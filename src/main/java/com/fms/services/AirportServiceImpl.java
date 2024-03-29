package com.fms.services;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.daos.AirportDao;
import com.fms.dtos.Airport;
//import com.fms.exceptions.AirportIdAlreadyExistException;
import com.fms.exceptions.AirportIdNotFoundException;

@Transactional
@Service("airportService")
public class AirportServiceImpl implements AirportService {

	@Autowired
	private AirportDao airportRepository;
	
	private List<Airport>airportList;
	/*@Autowired
	private ScheduleDao scheduleDao;*/

	public List<Airport> viewAirports()
	{
		airportList = airportRepository.findAll();
		return airportList;
	}
		
	@Override
	public Airport viewAirportById(BigInteger airportId)
	{
		Optional<Airport> findById = airportRepository.findById(airportId);
		if (findById.isPresent()) {
			return findById.get();
		}			
		else
		{
			throw new AirportIdNotFoundException("Airport not found with airport id: " + airportId);
	    }
	}

	
	/*
	@Override
	public Airport addAirport(Airport newAirport)
    {
        Optional<Airport> viewAirportByCode=airportRepository.viewAirportByCode(newAirport.getAirportCode());
        if(viewAirportByCode.isPresent())
        {
            throw new AirportIdAlreadyExistException();
        }
        return airportRepository.save(newAirport);
    }
	
	 @Override
	 public Airport updateAirport(Airport updateAirport)
	 {
		 Optional<Airport> viewAirportById = airportRepository.findById(updateAirport.getAirportId());
		 if(viewAirportById.isPresent())
		 {
	    	 return airportRepository.save(updateAirport);
	     }
	     else {
	    	 throw new  AirportIdNotFoundException(updateAirport.getAirportId()+" doesnot exist so cannot update the user.");
	    }
	}
	 
	 @Override
	 public void deleteAirport(Integer airportId) {
			Optional<Airport> oAirport = airportRepository.findById(airportId);
			if(oAirport.isPresent()) {
				airportRepository.deleteById(airportId);
			}
			else {
				throw new AirportIdNotFoundException("Airport Id is not found "+airportId);
			}
		}
	 */
	

	
	 
}