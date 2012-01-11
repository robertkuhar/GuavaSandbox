package org.rekdev.guava.collections.gtug;

import java.util.*;

import com.google.common.collect.*;

public class Multimaps {
    class Sale {
        public Sale( Salesperson s, int value ) {
        }

        public int getCharge() {
            return 0;
        }
    }

    class Salesperson {
        public Salesperson( String name ) {
        }
    }

    public static Comparator<Sale> SALES_CHARGE_COMPARATOR = new Comparator<Sale>() {
        @Override
        public int compare( Sale o1, Sale o2 ) {
            return o1.getCharge() - o2.getCharge();
        }
    };

    public Map<Salesperson,List<Sale>> map = new HashMap<Salesperson,List<Sale>>();

    public void makeSale_map( Salesperson salesPerson, Sale sale ) {
        List<Sale> sales = map.get( salesPerson );
        if ( sales == null ) {
            sales = new ArrayList<Sale>();
            map.put( salesPerson, sales );
        }
        sales.add( sale );
    }

    class SALES_CHARGE_COMPARATOR implements Comparator<Sale> {

        @Override
        public int compare( Sale o1, Sale o2 ) {
            return 0;
        }

    }
    
    public Sale getBiggestSale_map() {
        Sale biggestSale = null;

        for ( List<Sale> sales : map.values() ) {
            Sale myBiggestSale = Collections.max( sales, SALES_CHARGE_COMPARATOR );
            if ( biggestSale == null || myBiggestSale.getCharge() > biggestSale.getCharge() ) {
                biggestSale = myBiggestSale;
            }
        }
        return biggestSale;
    }

    // I love the "type inference" stuff that this .create() brings...
    public Multimap<Salesperson,Sale> multiMap = ArrayListMultimap.create();

    public void makeSale_multiMap( Salesperson salesPerson, Sale sale ) {
        multiMap.put( salesPerson, sale );
    }

    public Sale getBiggestSale_multiMap() {
        return Collections.max( multiMap.values(), SALES_CHARGE_COMPARATOR );
    }

}
