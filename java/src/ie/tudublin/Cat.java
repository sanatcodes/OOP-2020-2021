package ie.tudublin;

public class Cat extends Animal
{
    public Cat(String name)
    {
        super(name);
    }
    
    private int numLives = 9;

    public int getNumLives()
    {
        return numLives;
    }

    public void setNumLives(int num)
    {
        this.numLives = num;
    }

    public void kill(){
        numLives--;
        System.out.println("OUCH!!!");
        if(numLives == 0){
            System.out.println("Dead");
        }
    }
}