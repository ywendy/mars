package org.mars.user.algorithm;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;


import java.util.Collection;

/**
 *
 * tables num is 8
 *
 * @author tony
 * @date 2019/9/1
 */
public class UserTableAlgorithm extends AbstractShardingAlgorithm implements ComplexKeysShardingAlgorithm{
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {
        return sharding(availableTargetNames,shardingValue);
    }
}
