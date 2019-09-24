package org.mars.user.algorithm;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

/**
 * @author tony
 * @date 2019/9/24
 */
public abstract class AbstractShardingAlgorithm implements ComplexKeysShardingAlgorithm {


    protected Collection<String> sharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        int num = availableTargetNames.size()-1;
        Map<String, Collection<Object>> columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();
        System.out.println(columnNameAndShardingValuesMap);
        Set<Map.Entry<String, Collection<Object>>> entries = columnNameAndShardingValuesMap.entrySet();
        Iterator<Map.Entry<String, Collection<Object>>> entryIterator = entries.iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Collection<Object>> next = entryIterator.next();
            Collection<Object> values = next.getValue();
            for (Object obj : values) {
                Long v;
                if (obj instanceof Long) {
                    v = Long.parseLong(obj.toString());
                } else {
                    v = Long.parseLong(String.valueOf(obj.toString().hashCode()));
                }

                if (v != null) {
                    String suffix = String.valueOf(v & num);
                    for (Object targetName : availableTargetNames) {
                        String tmp = targetName.toString();
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
