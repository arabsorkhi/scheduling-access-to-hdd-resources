package FD_SCAN_alg;

import ArrayList.ArrayList;
import HDD_simulation.Head;
import HDD_simulation.RealTime_Request;
import HDD_simulation.Request;
import Sort.Sort;



public class FD_SCAN_handling {

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
    //list with real-time requests
    private ArrayList<Request> priority;

    public FD_SCAN_handling()
    {
        head = new Head();
        requests = new ArrayList<Request>();

        priority = new ArrayList<Request>();

        distance = 0;
    }

    public void handlingRequests() {
        /*
        sorts requests because it will be easier to look for requests that occur just between real-time tickets
         */
        sort();

        while (requests.isEmpty() || priority.isEmpty())
        {
            moveHead();
        }
        //result to compare algorithms
        System.out.println("Distance (FD_SCAN): " + distance + " units.");
    }

    private void moveHead() {

        //generate real-time request with probability
        generateReal();

        //check if there are real-time request
       checkPriority();

       if(priority.size()>1)
           //check if the are "normal" request between subsequent real-time request
            checkWay();

       /*
        determining whether the request that is currently being performed
        requires moving the head forward or backward and moving the head
         */
        if (tmpRequest.getWhichCell() > head.getPos()) {
            for (int i = head.getPos(); i < tmpRequest.getWhichCell(); i++) {
                distance++;
                head.incrementPos();
            }
        } else if (tmpRequest.getWhichCell() < head.getPos()) {
            for (int i = head.getPos(); i > tmpRequest.getWhichCell(); i--) {
                distance++;
                head.decrementPos();
            }
        }
            //head arrived to currently request
         if (tmpRequest.getWhichCell() == head.getPos() && priority.isEmpty())
            //remove request, which was done
             priority.remove(0);

            //head arrived to currently request
        else if (tmpRequest.getWhichCell() == head.getPos() && !priority.isEmpty())
            //remove request, which was done
             requests.remove(0);
    }

    private void generateReal() {
        //generate real-time request with probability
        if (Math.random() < 0.1)
        {
            for (int i = 0; i < 3; i++)
            {

                int randomCell = (int) ((Math.random() * 20) + 1);
                int randomTime = (int) ((Math.random() * 30) + 15);
                requests.add(new RealTime_Request(randomCell, randomTime));
            }

            //add real-time request to priority list
            addPriority();
        }
    }

    private void addPriority()
    {
        //add real-time request to priority list
        for(int i=0; i<requests.size(); i++)
            if(requests.get(i) instanceof RealTime_Request) {
                priority.add(requests.get(i));
                requests.remove(i);
            }

            /*
            sorts to make it easier to determine which ones to perform first and whether there are
            normal entries between them
             */
            Sort sort = new Sort(priority);
            priority = sort.getArray();
    }

    private void sort()
    {
        Sort sort = new Sort(requests);
        requests=sort.getArray();
    }

    //check if there are real-time request and set currently request to head
    private void checkPriority()
    {
        if(priority.isEmpty())
        {
            tmpRequest = priority.get(0);
        }
        else
            tmpRequest = requests.get(0);
    }

    //check if the are "normal" request between subsequent real-time request
    private void checkWay()
    {
        for(int i=0; i<requests.size(); i++)
        {
                if (requests.get(i).getWhichCell() < priority.get(1).getWhichCell() && requests.get(i).getWhichCell() > priority.get(0).getWhichCell())
                {
                    priority.add(0, requests.get(i));
                    requests.remove(i);
                }
        }

        Sort sort = new Sort(priority);
        priority=sort.getArray();
    }

    public void createRequest(Request tmp)
    {
        requests.add(tmp);
    }
}
