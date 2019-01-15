package smpous.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.stereotype.Service;

import smpous.models.Cinema;
import smpous.models.Rate;
import smpous.repositories.CinemaRepository;
import org.springframework.data.geo.Point;

@Service
public class BioskopSalaService extends AbstractCRUDService<Cinema, String>{
	
	private CinemaRepository cinemaRepository;
	
	public BioskopSalaService(CinemaRepository repo) {
		super(repo);
		this.cinemaRepository = repo;
		// TODO Auto-generated constructor stub
	}

	public HashSet<Cinema> findCinemaByName(String name)
	{
		return cinemaRepository.findCinemaByName(name);
	}
	
	public Cinema findCinemaById(String id)
	{
		return cinemaRepository.findCinemaById(id);
	}
	
	public List<Cinema> findAllCinema()
	{
		return cinemaRepository.findAll();
	}
	
	public List<Cinema> findNearPoint(Point p,Double maxDistance)
	{
		Distance distance = new Distance(maxDistance, Metrics.KILOMETERS);
		return cinemaRepository.findByLocationNear(p, distance);

	}
	
	public List<Cinema> findByNameAndAndAddressNear(String userOnSession, String name, Boolean isSort, double radius, double x, double y)
	{		
		Point p = new Point(x,y);
		
		List<Cinema> list = this.findNearPoint(p, radius);
		List<Cinema> filtered1 = new ArrayList<Cinema>();
		if(!name.equals("*"))
		{
			for(int i = 0; i < list.size(); i++)
			{
				if(list.get(i).getName().contains(name))
				{
					filtered1.add(list.get(i));
				}
			}
			
			list = filtered1;
		}
		
		if(isSort)
		{
			if(list.size() > 1)
			{ 
		        for(int i=0;i<list.size()-1;i++)
		        {
		            for(int j=0;j<list.size()-1;j++)
		            {
		                if(calcAvg(list.get(j).getRates())<calcAvg(list.get(j+1).getRates()))
		                {
		                	Cinema temp=list.get(j+1);
		                	list.set(j+1, list.get(j));
		                    list.set(j, temp);
		                }
		            }
	            }
			}
		}
		
		return list;
	}
	
	private float calcAvg(Map<String, Rate> map)
	{
	    float sum = 0;
	    int count = 0;
	    if(map == null)
	    {
	    	return 0;
	    }
		if(map.size() == 0)
		{
			return 0;
		}
		Iterator<String> it = map.keySet().iterator();
	    while (it.hasNext()) {
	        String key = it.next();
	        Rate yyy = map.get(key);
	        int yy = -1;
	        if(yyy.equals(Rate.one))
	        {
	        	yy = 1;
	        }
	        else if(yyy.equals(Rate.two))
	        {
	        	yy = 2;
	        }
	        else if(yyy.equals(Rate.three))
	        {
	        	yy = 3;
	        }
	        else if(yyy.equals(Rate.four))
	        {
	        	yy = 4;
	        }
	        else if(yyy.equals(Rate.five))
	        {
	        	yy = 5;
	        }
	          sum += yy;
	          count++;
	      }
	    float d = sum / count;
	    return d;
	}

}
