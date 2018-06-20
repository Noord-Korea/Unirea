package com.dbal.repository;

import com.dbal.Util;
import com.dbal.specification.Specifiable;
import com.models.Town;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.MapsId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TownRepository extends AbstractRepository<Town, Integer> {
    @Override
    public Class<Town> getDomainClass() {
        return Town.class;
    }

    public List<Town> findAllNoDuplicates(Specifiable spec) {
        List<Town> towns = findAll(spec);
        Map<Integer, Town> townsNoDuplicate = new HashMap<>();
        for (Town town : towns) {
            townsNoDuplicate.put(town.getId(), town);
        }

        return new ArrayList<Town>(townsNoDuplicate.values());
    }
}
