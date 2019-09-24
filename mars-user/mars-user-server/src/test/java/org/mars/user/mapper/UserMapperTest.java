package org.mars.user.mapper;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mars.common.security.MD5Util;
import org.mars.user.domain.User;
import org.mars.user.util.NameBuilder;
import org.mars.user.util.RandomName;
import org.mars.user.util.UidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author tony
 * @date 2019/8/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    private static final String[] email_suffix = "@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UidGenerator uidGenerator;

    @Test
    public void testSelectByKey() {
        User user = userMapper.selectByUid(63203202757137698L);
        System.out.println(user);
    }


    @Test
    public void testSelectByLoginName(){
        User user = userMapper.selectByLoginName("suxiaban_8");
        System.out.println(user);
    }


    @Test
    public void testInsert() throws IOException, BadHanyuPinyinOutputFormatCombination, InterruptedException {

     /*   List<Map<String, String>> nameList = new ArrayList<>();
        InputStream in = UserMapper.class.getClassLoader().getResourceAsStream("renming.txt");

        List<String> list = IOUtils.readLines(in, "gbk");

        list.parallelStream().forEach(str -> {
            if (str!=null){
                String[] names = str.split("#");
                for (String name : names) {
                    if (name != null && !"".equals(name.trim()) && name.length() > 1) {

                        try {
                            String tmpName = new String(name.getBytes("utf8"));

                            Map<String, String> nameMap = new HashMap<>();
                            String piny = pingyin(tmpName).toLowerCase();
                            System.err.println("--------" + tmpName + "---" + piny + "--------------");
                            nameMap.put("zhongwen", tmpName);
                            nameMap.put("yingwen", piny);
                            nameList.add(nameMap);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                            badHanyuPinyinOutputFormatCombination.printStackTrace();
                        }
                    }
                }
            }


        });


        int size = list.size();*/
        ExecutorService executor = Executors.newFixedThreadPool(50);


        for (int n = 0; n < 100; n++) {
            executor.submit(() -> {
                for (int j = 0; j < 10000; j++) {
                    //System.out.println("begin================");
                    try {
                        int i = ThreadLocalRandom.current().nextInt(2);
                        User record = new User();

                        String name = NameBuilder.build();
                        String pingyin = pingyin(name).toLowerCase();
                        record.setName(name);
                        int i1 = ThreadLocalRandom.current().nextInt(10);
                        if (i1<1){
                            i1=5;
                        }
                        record.setNickName(RandomName.getRandomJianHan(i1));
                        record.setPhone(getTel());
                        record.setEmail(getAlpha(name).toLowerCase() + email_suffix[(int) (Math.random() * email_suffix.length)]);
                        record.setGender(i == 1 ? (byte) 1 : (byte) 2);
                        int age = ThreadLocalRandom.current().nextInt(50);
                        record.setAge(age);
                        String salt = UUID.randomUUID().toString().replaceAll("-","");
                        record.setPwd(MD5Util.md5(pingyin,salt));
                        record.setSalt(salt);
                        record.setAvatar("/profile/avatar/" + pingyin + ".png");
                        String login_name = pingyin + "_" + ThreadLocalRandom.current().nextInt(1000);
                        record.setLoginName(login_name);
                        int hash = login_name.hashCode();
                        System.err.println("---------------"+hash%2+"---"+hash%8+"-------------------------");
                        record.setUid(uidGenerator.nextId(hash));
                        userMapper.insertSelective(record);
                        //System.out.println("--------------" + j + "-------------------------");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(j + "----------error-----------------");
                    }
                    //System.out.println("end================");
                }
            });
        }

        executor.awaitTermination(10000, TimeUnit.SECONDS);





    /*    User record = new User();
        //record.setName();


        int i = userMapper.insertSelective(record);
        assertEquals(i,1);*/
    }





    /**
     * 返回手机号码
     */
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    private static String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }


    private String pingyin(String name) throws BadHanyuPinyinOutputFormatCombination {
        try {
            char[] charArray = name.toCharArray();
            StringBuilder pinyin = new StringBuilder();
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
            //设置大小写格式
            defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
            //设置声调格式：
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            for (int i = 0; i < charArray.length; i++) {
                //匹配中文,非中文转换会转换成null
                if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat);
                    String string = hanyuPinyinStringArray[0];
                    pinyin.append(string);
                } else {
                    pinyin.append(charArray[i]);
                }
            }

            return pinyin.toString();

        } catch (Exception e) {

        }


        return "xxxx";
    }

    private final static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

    public static String strFirst2Pinyin(String str) {

        try {
            StringBuffer sb = new StringBuffer();
            //判断是中文


            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                //1.判断是不是中文
                if (c >= '\u4e00' && i <= '\u9fa5') {


                    sb.append(PinyinHelper.toHanyuPinyinStringArray(c, format)[0]).charAt(0);

                } else {

                }

            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // 返回中文的首字母
    public static String getAlpha(String chines) {
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(
                            nameChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName += nameChar[i];
            }
        }
        return pinyinName;
    }

    public static void main(String[] args) {
        System.out.println(getAlpha("姚健"));
    }


}