package org.mars.user.algorithm;

import org.apache.commons.lang.StringUtils;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

/**
 * @author yaojian
 * @date 2019/9/10
 */
public class UserDatabaseAlgorithm implements ComplexKeysShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {
/**
 * {uid=[62600060499658553], login_name=[fanhen_a78aaac1c26e4c75b9d5ec405f0d7ef1]}
 */

        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Map<String, Collection<Object>> columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();


        System.out.println(columnNameAndShardingValuesMap);


        Set<Map.Entry<String, Collection<Object>>> entries = columnNameAndShardingValuesMap.entrySet();
        Iterator<Map.Entry<String, Collection<Object>>> entryIterator = entries.iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Collection<Object>> next = entryIterator.next();
            String key = next.getKey();
            Collection<Object> values = next.getValue();
            for (Object obj : values) {
                Long v;
                if (StringUtils.equalsIgnoreCase("uid", key)) {
                    v = Long.parseLong(obj.toString());
                } else {
                    v = Long.parseLong(String.valueOf(obj.toString().hashCode()));
                }
                if (v != null) {
                    String suffix = String.valueOf(v & 1);
                    for (Object dbName : availableTargetNames) {
                        String tmp = dbName.toString();
                        if (tmp.endsWith(suffix)) {
                            result.add(tmp);
                            return result;
                        }

                    }

                }

            }


        }


        return result;
    }
}
