package com.mybatis.logtest;

import java.io.File;

public class CreateLogDir {
    public static void main(String[] args) {
        File logDir = new File("logs");
        if (!logDir.exists()) {
            if (logDir.mkdir()){
                System.out.println("logs 디렉토리 생성 완료");
            }else {
                System.out.println("logs 디렉토리 생성 실패");
            }
        }else {
            System.out.println("logs 디렉토리가 존재합니다.");
        }
    }
}
