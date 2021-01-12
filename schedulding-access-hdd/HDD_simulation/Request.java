package HDD_simulation;

public class Request {

    //determining the address of the request via a cell in the disk's memory
    private int whichCell;

    public Request(int whichCell)
    {
        this.whichCell=whichCell;
    }

    public int getWhichCell() {
        return whichCell;
    }
}
