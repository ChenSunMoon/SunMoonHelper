package com.sunmoon.helper.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 法判断汉字的拼音是否相同
 */
public class HanyuPinyin {
    private static boolean equalsOnePinYing(String[] str1, String[] str2) {
        boolean flag=false;
        for (int i=0;i< str1.length;i++){
            for (int j=0;j< str2.length;j++){
                if (str1[i].equals(str2[j]))
                    flag=true;
            }
        }
        return flag;
    }
    /**
     * 把汉字转换成拼音
     */
    private static String[] ChineseToPinying(char str){
        String[] strs = new String[0];
        char[] nameChar= {str};
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++)
        {
            if (nameChar[i] > 128)
            {
                try {
                    // 取得当前汉字的所有全拼
                    strs = PinyinHelper.toHanyuPinyinStringArray(
                            nameChar[i], defaultFormat);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }
        }

        return strs;
    }
    /**
     * 字符串和字符串比较
     */
    public static boolean equalsLinePinYing(String str1, String str2)
    {
        char[] chars1=str1.toCharArray();
        char[] chars2=str2.toCharArray();
        if (str1.equals(str2))
        {
            return true;
        }
        else
        {
            if (chars1.length!=chars2.length)
            {
                return false;
            }
            else
            {

                for (int i=0;i<chars1.length;i++)
                {
                    if ( chars1[i]<128&&chars2[i]<128){
                        String string1= String.valueOf(chars1[i]).toLowerCase();
                        String string2= String.valueOf(chars2[i]).toLowerCase();
                        if (string1!=string2){
                            return false;
                        }
                    }
                    if (!equalsOnePinYing(ChineseToPinying(chars1[i]),ChineseToPinying(chars2[i])))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    }

