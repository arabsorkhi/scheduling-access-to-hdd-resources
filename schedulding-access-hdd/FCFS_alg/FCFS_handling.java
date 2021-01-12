package FCFS_alg;

import ArrayList.ArrayList;
import HDD_simulation.Head;
import HDD_simulation.Request;

public class FCFS_handling {

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

    public FCFS_handling()
    {
        head = new Head();
        requests = new ArrayList<Request>();

        distance =0;

        tmpRequest=null;
    }

    public void handlingRequests()
    {
        //the condition for stopping the algorithm is to flush the entire list of requests
        while(requests.isEmpty())
        {
            tmpRequest = requests.get(0);
           moveHead(tmpRequest);
        }

        //result to compare algorithms
        System.out.println("Distance (FCFS): " + distance + " units.");
    }

    private void moveHead(Request tmpRequest)
    {
        /*
        determining whether the request that is currently being performed
        requires moving the head forward or backward and moving the head
         */
        if(tmpRequest.getWhichCell() > head.getPos()) {
            for (int i = head.getPos(); i < tmpRequest.getWhichCell(); i++)
            {
                distance++;
                head.incrementPos();
            }
        }

        else if(tmpRequest.getWhichCell() < head.getPos())
        {
            for (int i = head.getPos(); i > tmpRequest.getWhichCell(); i--)
            {
                distance++;
                head.decrementPos();
            }
        }

        //head arrived to currently request
        else if (tmpRequest.getWhichCell() == head.getPos())
            //remove request, which was done
            requests.remove(0);

    }

    public void createRequest(Request tmp)
    {
        requests.add(tmp);
    }
}
