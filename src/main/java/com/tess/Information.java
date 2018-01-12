package com.tess;

/**
 * Created by 618 on 2018/1/8.
 * @author lingfengsan
 */
public class Information {
    private String question;
    private String[] ans;
    
    public Information(String str) {
        str = str.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");    // 替换空行
        str=str.replaceAll(" ","");    // 替换空格
        str=str.replaceAll("(\\d+\\.)", "$1").replaceAll("(\\d+\\.)", ""); // 替换题号
        question = str.trim().substring(0,str.indexOf(Constant.QUESTION_FLAG) + 1);
        String remain = str.substring(str.indexOf(Constant.QUESTION_FLAG) + 1);
        ans = remain.trim().split("\n");
    }

    public String getQuestion() {
        return question;
    }
    
    public String[] getAns() {
        return ans;
    }

    public static void main(String[] args) {
        String testStr = "8..阿尔茨海默症又被称为什么?\n" +
                "老年痴呆症\n" +
                "\n" +
                "癫痫症\n" +
                "\n" +
                "小儿麻痹症";
        Information information= new Information(testStr);
        String que=information.getQuestion();
        String[] ans=information.getAns();
        System.out.println(que);
        for (String an : ans) {
            System.out.println(an);
        }
    }
}
