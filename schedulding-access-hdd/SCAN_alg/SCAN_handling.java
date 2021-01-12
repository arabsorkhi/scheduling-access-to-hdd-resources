package SCAN_alg;

import ArrayList.ArrayList;
import HDD_simulation.Head;
import HDD_simulation.Request;

public class SCAN_handling{

    //I thought it would be best if each simulation would move its head
    private Head head;
    //this is the variable that determines the "current purpose" of the head
    private Request tmpRequest;

    /*
    variable 'distance' stores information about how many cells the head has traveled will be used to measure the
    performance of the algorithms
     */
    private int distance;
    private int tmpIndex;

    //"to-do list"
    private ArrayList<Request> requests;

    public SCAN_handling()
    {
        head = new Head();
        requests = new ArrayList<Request>();

        distance =0;

        tmpRequest=null;
    }


    public void handlingRequests()
    {
        //extremeCase described below
        extremeCase();

        //the condition for stopping the algorithm is to flush the entire list of requests
        while(requests.isEmpty())
        {
            moveHead(tmpRequest);
        }

        //result to compare algorithms
        System.out.println("Distance (SCAN): " + distance + " units.");
    }

    private void moveHead(Request tmpRequest)
    {
        /*
        determining whether the request that is currently being performed
        requires moving the head forward or backward and moving the head
         */
        if(head.getPos() < tmpRequest.getWhichCell())
        {
            for(int i=head.getPos(); i<tmpRequest.getWhichCell(); i++)
            {
                head.incrementPos();
                distance++;
            }
        }

        else if(head.getPos() > tmpRequest.getWhichCell())
        {
            for(int i=head.getPos(); i>tmpRequest.getWhichCell(); i--)
            {
                head.decrementPos();
                distance++;
            }
        }
        //head arrived to currently request
        else if (head.getPos() == tmpRequest.getWhichCell())
        {
            changeRequest();
        }
    }

    private void changeRequest()
    {

        //variable stores old index (help to help in deciding which way to look for a new request)
        int oldIndex = tmpIndex;

        //remove request, which was done
        requests.remove(oldIndex);


        //looking for a request at a higher cell address
        for(int i = oldIndex; i<requests.size(); i++)
        {
            if(requests.get(i).getWhichCell() > head.getPos())
            {
                tmpIndex = i;
                tmpRequest = requests.get(tmpIndex);
                break;
            }
        }

        //if not found at a higher address then search at a lower one
        if(oldIndex == tmpIndex)
        {
            for(int i = oldIndex -1; i>0; i--)
            {
                if(requests.get(i).getWhichCell()< head.getPos())
                {
                    tmpIndex=i;
                    tmpRequest = requests.get(tmpIndex);
                    break;
                }
            }
        }

        /*
        "extreme case" is that there is no new address, neither the higher nor the lower address, so the new request is
         at the zero position of the request list (this is the case when, for example, the requests are "15 16 13 9 8 7 ..." )
         */
        if(oldIndex == tmpIndex && requests.size() > 0)
        {
            extremeCase();
        }

    }

    private void extremeCase()
    {
        tmpRequest=requests.get(0);
        tmpIndex=0;
    }

    public void createRequest(Request tmp)
    {
        requests.add(tmp);
    }

}