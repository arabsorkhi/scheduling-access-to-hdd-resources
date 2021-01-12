package HDD_simulation;

public class Head {

    //currently head position
    private int pos;

    public Head()
    {
        pos=0;
    }

    //increment currently position
    public void incrementPos()
    {
        this.pos++;
    }
    //decrement currently position
    public void decrementPos()
    {
        this.pos--;
    }

    public int getPos()
    {
        return pos;
    }

    //set position
    public void setPos(int pos)
    {
        this.pos=pos;
    }
}
