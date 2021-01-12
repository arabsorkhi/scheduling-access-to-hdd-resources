package C_SCAN_alg;


import ArrayList.ArrayList;
import HDD_simulation.Head;
import HDD_simulation.Request;

public class C_SCAN_handling{

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

    public C_SCAN_handling()
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
        System.out.println("Distance (C_Scan): " + distance + " units.");
    }

    private void moveHead(Request tmpRequest)
    {
        /*
        here the head only moves up, if there is no request for a higher cell then the head returns to the beginning
         */
        if(head.getPos() < tmpRequest.getWhichCell())
        {
            for(int i=head.getPos(); i<tmpRequest.getWhichCell(); i++)
            {
                head.incrementPos();
                distance++;
            }
        }

        //head arrived to currently request
        else if (head.getPos() == tmpRequest.getWhichCell() && requests.size() >0)
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

        //if not found at a higher address then move the head to the beginning
        if(oldIndex == tmpIndex && requests.size() > 0)
        {
            extremeCase();
            //return to the beginning means traveling the amount of cells at which the head is currently located
            distance+=head.getPos();
            head.setPos(0);
        }
    }

    //moving the head to the beginning
    private void extremeCase()
    {
        tmpRequest=requests.get(0);
        tmpIndex=0;
    }

    //create requests
    public void createRequest(Request tmp)
    {
        requests.add(tmp);
    }
}