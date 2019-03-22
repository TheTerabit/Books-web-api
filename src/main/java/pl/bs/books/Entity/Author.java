package pl.bs.books.Entity;

public class Author {
    private String name;
    private double[] rates;
    private int numberOfBooks;

    public Author(String name) {
        this.name = name;
        numberOfBooks=0;
    }
    public Author() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBook(double rate){
        rates[numberOfBooks]=rate;
        numberOfBooks++;
    }

    public double getAvarageRating(){
        double sum=0.0;
        for(int i=0;i<numberOfBooks;i++){
            sum+=rates[i];
        }
        return sum/numberOfBooks;
    }

}
