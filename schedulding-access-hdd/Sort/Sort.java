package Sort;

import ArrayList.ArrayList;
import HDD_simulation.Request;

public class Sort {

    /*
    here, generic sorting was supposed to be done nicely, but I found that it makes no sense if we are
    considering making a program for specific requirements, it is also a useless complication
     */
    private ArrayList<Request> array;

    public Sort(ArrayList<Request> array)
    {
        this.array=array;
    }

//traditional bubble sort
  private void BubbleSort()
  {
      int n = array.size();

      for(int i=0; i< n-1; i++)
      {
          for(int j=0; j< n-i-1; j++)
          {
              if(array.get(j).getWhichCell() > (int) array.get(j+1).getWhichCell())
              {
                  Request temp = array.get(j);
                  array.add(j,  array.get(j+1));
                  array.remove(j+1);
                  array.add(j+1, temp);
                  array.remove(j+2);
              }
          }
      }
  }

  public ArrayList<Request> getArray()
  {
      BubbleSort();
      return array;
  }
}
