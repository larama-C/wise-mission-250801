package com.back;

import java.util.Scanner;

class Quote {                       //명언 클래스
    int id = 0;                     //명언 ID
    String content;                 //
    String author;

    public Quote(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}

public class Main {

    public static boolean CheckQuote(Quote[] quotes, int id) {
        if ((id < 1) || (quotes[id - 1] == null) || (quotes[id - 1].content == null)) {
            return false;
        } else {
            return true;
        }
    }

    public static void printQuote(Quote[] quotes, int indexCount) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = indexCount - 1; i >= 0; i--) {
            if (quotes[i] != null && quotes[i].content != null) {
                System.out.printf("%d / %s / %s\n", quotes[i].id, quotes[i].author, quotes[i].content);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Quote[] quotes = new Quote[100];
        int indexCount = 0;
        boolean running = true;

        System.out.println("== 명언 앱 ==");

        while (running) {
            System.out.print("명령) ");
            String input = scan.nextLine();

            if (input.equals("등록")) {
                System.out.print("명언 : ");
                String content = scan.nextLine();
                System.out.print("작가 : ");
                String author = scan.nextLine();
                quotes[indexCount] = new Quote(indexCount + 1, content, author);
                indexCount++;
                System.out.println((indexCount) + "번 명언이 등록되었습니다.");
            } else if (input.equals("목록")) {
                printQuote(quotes, indexCount);
            } else if (input.startsWith("삭제?id=")) {
                String idStr = input.split("=")[1];
                int id = Integer.parseInt(idStr);

                if (!CheckQuote(quotes, id)) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                } else {
                    quotes[id - 1].content = null;
                    quotes[id - 1].author = null;
                    System.out.println(id + "번 명언이 삭제되었습니다.");
                }
            } else if (input.startsWith("수정?id=")) {
                String idStr = input.split("=")[1];
                int id = Integer.parseInt(idStr);

                if (!CheckQuote(quotes, id)) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                } else {
                    System.out.printf("명언(기존) : %s\n", quotes[id - 1].content);
                    System.out.print("명언 : ");
                    quotes[id - 1].content = scan.nextLine();
                    System.out.printf("작가(기존) : %s\n", quotes[id - 1].author);
                    System.out.print("작가 : ");
                    quotes[id - 1].author = scan.nextLine();
                }
            } else if (input.equals("종료")) {
                running = false;
            } else {
                System.out.println("알 수 없는 명령입니다. 예: 등록, 목록, 삭제?id=1, 수정?id=2, 종료");
            }
        }
    }
}