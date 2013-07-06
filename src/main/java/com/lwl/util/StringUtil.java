package com.lwl.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component("stringUtil")
public class StringUtil {
	
	public String truncStr(String src, int index) {
		String truncStr = src;
		
		if (src.length() > index) {
			truncStr = src.substring(0, index);
			truncStr = truncStr + "...";
		}
		
		return truncStr;
	}
	
	public String truncStr(String src, int index, String suffix) {
		String truncStr = src;
		
		if (src.length() > index) {
			truncStr = src.substring(0, index);
			
			if (suffix != null) {
				truncStr = truncStr + "...";
			} 
		}
		
		return truncStr;
	}

	 /**
     * �������Զ�
     * 
     * @param paramStr ���Զ��ַ���
     * @param separators ���ԶԷָ�������ǰ����,���ȼ��ߵ��ȴ���   �磺"name=lwl&age=25" ����:&,=
     * @return
     */
    public Map<String, String> getParameterPair(String paramStr, String... separators) {
        Map<String, String> paras = new HashMap<String, String>();
        
        if (paramStr != null) {
            //��ʼ��
            String[] paramArray = new String[]{paramStr.trim()};

            //��ʼ��
            int size = 0;
            
            executeParse(paras, paramArray, size, separators);
        }

        return paras;
    }
    
    //��Ͻ������Զԣ���һ����������
    private void executeParse(Map<String, String> params, String[] paramArray,  int size, String... separators) {

        int moreSize = size + 1;
        for (int i = 0; i < paramArray.length; i++) {
            String sss = paramArray[i];
            String[] paramStrs = sss.split(separators[size]);
            
            if (moreSize < separators.length) {
                executeParse(params, paramStrs, moreSize, separators);
            } else {
                if (paramStrs.length == 2) {
                    params.put(paramStrs[0], paramStrs[1]);
                }
                else if (paramStrs.length == 1) {
                    params.put(paramStrs[0], "");
                }

            }
        }
    }
    
	/**
	 * ���src����λ������comp��ȫ
	 * @param src
	 * @param length
	 * @param isLeft true:��ʾ������ߣ�false����ʾ�����ұ�
	 * @return
	 */
	public static String getStringCompletion(String src, int length,  String comp ,boolean isLeft) {
		int len = length - src.length();
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				if (isLeft) {
					src = comp + src;
				} else {
					src += comp;
				}
			}
		}
		
		return src;
	}
	
	/**
	 * ����ַ������ֽڳ��ȣ���������ģ���Ϊ�������ֽ�
	 * @param str
	 * @return
	 */
	public static int getByteSize(String str) {
		int size = 0;
		
		if (str != null) {
			for (int i = 0; i < str.length(); i++) {
				String c = str.substring(i, i+1);
				if (c.matches("[\\u4e00-\\u9fa5]")){
					size += 2;
				} else {
					size += 1;
				}
			}
		}
		
		return size;
	}
	
	/**
	 * ��ȡ�ַ��������Ȳ���ʱ�����ᱨ��
	 * @param src
	 * @param start
	 * @param end
	 * @return
	 */
	public static String subString(String src, int start, int end) {
		if (src == null) {
			return "";
		}
		
		int length = src.length();
		if (start > length) {
			return "";
		} else if (start == length) {
			return src.substring(start);
		}
		
		if (length < end) {
			end = length;
		}
		
		return src.substring(start, end);
	}
}
