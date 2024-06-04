package com.ceva.section5.poo;

public class MMovie {
    String title;
    String genre;
    int rating;

    void playIt(){
        System.out.println("Playing the movie " + title);
    }

    public static void main(String[] args) {
        MMovie one = new MMovie();
        one.title = "Gone with the Stock";
        one.genre = "Tragic";
        one.rating = -2;

        MMovie two = new MMovie();
        two.title = "Lost in the Force";
        two.genre = "Fantasy";
        two.rating = 5;
        two.playIt();

        MMovie three = new MMovie();
        three.title = "The Hobbit";
        three.genre = "Action";
        three.rating = 122;
        three.playIt();
    }
}
