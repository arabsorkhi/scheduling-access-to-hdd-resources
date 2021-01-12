package HDD_simulation;

import ArrayList.ArrayList;

public class HDD_object {

    //variable stores hard disk size (not final, because I want give the user control)
    private  int size;
    /*
    list stores cells (help to create new request, that there would be no two requests with the same address
    (in real, this means saving the data on the already used cell))
     */
    private ArrayList<Boolean> cells;

    public HDD_object(int size)
    {
        this.size=size;
        cells = new ArrayList<Boolean>(size);
        setStartAvailable();
    }


    public ArrayList<Boolean> getCells()
    {
        return cells;
    }

    //when the program starts the disk is empty, i.e. the cells are free
    private void setStartAvailable()
    {
        for (int i=0; i<size; i++)
        {
            cells.add(true);
        }
    }

    public int getSize()
    {
        return size;
    }


}
