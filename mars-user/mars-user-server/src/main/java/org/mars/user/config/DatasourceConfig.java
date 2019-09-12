package org.mars.user.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ComplexShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mars.user.algorithm.UserDatabaseAlgorithm;
import org.mars.user.algorithm.UserShardingTableAlgorithm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author yaojian
 * @date 2019/9/1
 */
@Configuration
@Slf4j
public class DatasourceConfig {

    @Bean(name = "user_0")
    @Qualifier("user_0")
    @ConfigurationProperties("spring.user0")
    public DataSource zeroDatasource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "user_1")
    @Qualifier("user_1")
    @ConfigurationProperties("spring.user1")
    public DataSource firstDatasource() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    @Primary
    public DataSource dataSource() throws SQLException {
        return shardingDataSource();
    }


    private DataSource shardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRulesConfiguration());
        //shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("uid", "user_${uid % 2}"));
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new ComplexShardingStrategyConfiguration("uid,login_name",new UserDatabaseAlgorithm()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("uid", new UserShardingTableAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(),
                shardingRuleConfig, new Properties());


    }

    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>(8);
        dataSourceMap.put("user_0", zeroDatasource());
        dataSourceMap.put("user_1", firstDatasource());
        return dataSourceMap;
    }


    TableRuleConfiguration getUserTableRulesConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("t_user", "user_${0..1}.t_user_${0..7}");
        //result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
        return result;
    }


  /*  private KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
        return new KeyGeneratorConfiguration("SNOWFLAKE", "uid", new Properties());
    }
*/





}
