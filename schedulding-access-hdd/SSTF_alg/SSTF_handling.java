package SSTF_alg;

import ArrayList.ArrayList;
import HDD_simulation.Head;
import HDD_simulation.Request;
import Sort.Sort;

public class SSTF_handling {

    //I thought it would be best if each simulation would move its head
    private Head head;
    //this is the variable that determines the "current purpose" of the head
    private Request tmpRequest;

    /*
    variable 'distance' stores information about how many cells the head has traveled will be used to measure the
    performance of the algorithms
     */
    private int distance;

    //"to-do list"
    private ArrayList<Request> requests;

    public SSTF_handling()
    {
        head = new Head();
        requests = new ArrayList<Request>();

        distance =0;

        tmpRequest=null;
    }

    public void handlingRequests()
    {
        //sort, because the SSTF algorithm is to make requests that follow each other
        sortRequests();

        //the condition for stopping the algorithm is to flush the entire list of requests
        while(requests.isEmpty())
        {
            tmpRequest = requests.get(0);
            moveHead(tmpRequest);
        }
        //result to compare algorithms
        System.out.println("Distance (SSTF): " + distance + " units.");
    }

    private void moveHead(Request tmpRequest)
    {
        /*
        if we have sort the requests in turn, the head will move only upwards
         */
        for(int i=head.getPos() ; i < tmpRequest.getWhichCell(); i++)
        {
            distance++;
            head.incrementPos();
        }
        //head arrived to currently request
         if (tmpRequest.getWhichCell() == head.getPos())
        //remove request, which was done
        requests.remove(0);
    }

    //sorting requests
    private void sortRequests()
    {
        Sort help = new Sort(requests);
        this.requests= help.getArray();
    }

    public void createRequest(Request tmp)
    {
        requests.add(tmp);
    }

}
