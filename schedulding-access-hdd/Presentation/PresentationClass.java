package Presentation;


import ArrayList.ArrayList;
import C_SCAN_alg.C_SCAN_handling;
import EDF_alg.EDF_handling;
import FCFS_alg.FCFS_handling;
import FD_SCAN_alg.FD_SCAN_handling;
import HDD_simulation.HDD_object;
import HDD_simulation.Request;
import SCAN_alg.SCAN_handling;
import SSTF_alg.SSTF_handling;

public class PresentationClass {

    private HDD_object hdd;
    private FCFS_handling fcfs;
    private C_SCAN_handling cscan;
    private EDF_handling edf;
    private FD_SCAN_handling fdscan;
    private SCAN_handling scan;
    private SSTF_handling sstf;

    private ArrayList<Request> show;

    public PresentationClass()
    {
        //here user can change hdd size
        hdd = new HDD_object(100);

        fcfs = new FCFS_handling();
        cscan = new C_SCAN_handling();
        edf = new EDF_handling();
        fdscan = new FD_SCAN_handling();
        scan = new SCAN_handling();
        sstf = new SSTF_handling();

        show = new ArrayList<Request>();

        createRequest();
    }

    public void requestHandling()
    {
        System.out.println("Algorithms without real-time requests:");
        fcfs.handlingRequests();
        cscan.handlingRequests();
        scan.handlingRequests();
        sstf.handlingRequests();

        System.out.println("\nAlgorithms with real-time requests:");
        edf.handlingRequests();
        fdscan.handlingRequests();

        System.out.println("\nIt should be corrected that real-time request is generated in EDF and FD-SCAN algorithms");
    }

    //here user can change conditions (how many request, how addresses)
   private void createRequest()
    {
        for(int i=0; i<1000; i++)
        {
            int tmp = (int) ((Math.random()*hdd.getSize()-1)+1);

            //generate requests
            if(hdd.getCells().get(tmp))
            {
                fcfs.createRequest(new Request(tmp));
                cscan.createRequest(new Request(tmp));
                edf.createRequest(new Request(tmp));
                fdscan.createRequest(new Request(tmp));
                scan.createRequest(new Request(tmp));
                sstf.createRequest(new Request(tmp));

                show.add(new Request(tmp));

                hdd.getCells().remove(tmp);
                hdd.getCells().add(tmp, false);
            }
        }
    }

    public void displayRequest()
    {
        System.out.println("\n\nThe algorithms had to deal with requests for such cell addresses:");
        for(int i=0; i<show.size(); i++)
            System.out.print(show.get(i).getWhichCell() + " , ");

        System.out.println("\n\nThere was to be done " + show.size() + " requests.");
        System.out.println("\n");
    }


}
