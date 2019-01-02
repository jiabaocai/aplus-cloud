package com.ald.cloud.zuul.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
  *                  ,___         .-;'
  *                  `"-.`\_...._/`.`
  *             ,       \        /
  *         .-' ',      / ()   ()\
  *         `'._ \     /()    .  (|
  *             > .'  ;,     -'-  /
  *            / <   |;,     __.;
  *            '-.'-.|  , \    , \
  *               `>.|;, \_)    \_)
  *                `-;     ,    /
  *                  \    /   <
  *                  '. <`'-, _)
  *                  '._)
  * 
  * @Author luohao
  * @Version 1.0.0
  * @Date 15:33 2017/11/29
  * @Desription 
  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
  */
 
public class FileUtil {

    public static String printMethodFromFile(File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                if(tempString.contains("@FilterOrder") && !tempString.contains("import")){
                    if(tempString.contains("*") || tempString.contains("//")){
                        continue;
                    }else{
                        break;
                    }

                }
            }
            reader.close();

            return tempString;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return null;
    }
    
}
