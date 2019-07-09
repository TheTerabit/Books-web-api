package pl.bs.books.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {
    private String name;
    private double[] rates;
    private int numberOfBooks;

    public Author(String name, double rate) {
        this.name = name;
        numberOfBooks=1;
        rates=new double[1000];
        rates[0]=rate;

    }
    public Author() {}

    public void addBook(double rate){
        rates[numberOfBooks]=rate;
        numberOfBooks++;
    }

    public double getAverageRating(){
        double sum=0.0;
        for(int i=0;i<numberOfBooks;i++){
            sum+=rates[i];
        }
        return sum/numberOfBooks;
    }

}