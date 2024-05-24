package com.example.buyer;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MyBoard {
    private int id;
    private String title;
    private MyUser myUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    @Override
    public String toString() {
        return "MyBoard{" +
                "id=" + id +
                ", title='" + title + '\'' +

                '}';
    }
}

class MyUser {
    private int id;
    private String username;

    private List<MyBoard> boardList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<MyBoard> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<MyBoard> boardList) {
        this.boardList = boardList;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", boardList=" + boardList +
                '}';
    }
}

public class HelloTest {

    // ssar (1,2)
    // 1게시글 -> ssar
    // 2게시글 -> ssar
    @Test
    public void to_test(){
        MyUser myUser = new MyUser();
        myUser.setId(1);
        myUser.setUsername("ssar");


        MyBoard board1= new MyBoard();
        board1.setId(1);
        board1.setTitle("제목1");
        board1.setMyUser(myUser);

        MyBoard board2 = new MyBoard();
        board2.setId(2);
        board2.setTitle("제목2");
        board2.setMyUser(myUser);

        List<MyBoard> boardList = Arrays.asList(board1, board2);
        myUser.setBoardList(boardList);

        System.out.println(myUser);

    }
}
