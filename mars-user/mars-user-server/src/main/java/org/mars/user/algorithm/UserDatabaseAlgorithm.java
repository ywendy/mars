package org.mars.user.algorithm;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.Map;

/**
 * @author yaojian
 * @date 2019/9/10
 */
public class UserDatabaseAlgorithm implements ComplexKeysShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {


        Map<String,Collection<Object>> columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();



        return null;
    }
}
