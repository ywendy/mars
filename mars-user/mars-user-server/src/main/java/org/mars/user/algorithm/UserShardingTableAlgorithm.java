package org.mars.user.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author yaojian
 * @date 2019/9/1
 */
public class UserShardingTableAlgorithm implements PreciseShardingAlgorithm<Long> {


    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {

        Long id = shardingValue.getValue();
        String suffix = String.valueOf(id & 7);
        for (String tableName : availableTargetNames) {
            if (tableName.endsWith(suffix)) {
                return tableName;
            }
        }
        return "";
    }
}
