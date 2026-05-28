@RestController  
@RequestMapping("/api/v1/parkinglot/{parkingLotId}")

public class ParkingLotController{
  private final ParkingLotService;
  public ParkingLotController(ParkingLotService parkingLotService){
    this.ParkingLotService = parkingLotService;
  }
}

