package org.mars.user.algorithm;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

/**
 *
 * databases num is two.
 *
 * @author tony
 * @date 2019/9/10
 */
public class UserDatabaseAlgorithm extends AbstractShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {
        /**
         * {uid=[62600060499658553], login_name=[fanhen_a78aaac1c26e4c75b9d5ec405f0d7ef1]}
         */
        return sharding(availableTargetNames, shardingValue);
    }
}
