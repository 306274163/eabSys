package com.eastpro.dao;
import com.eastpro.entity.SysUser;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@SuppressWarnings("serial")
@Repository
public class SysUserDAOImpl extends BaseDaoImpl implements SysUserDAO {
    @Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public SysUser findUserByName(String name) {
        // TODO Auto-generated method stub
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SysUser.class);
        if(name!=null && !"".equals(name))
            criteria.add(Restrictions.eq("personId", name));
        System.out.println(">>>>>>>>>>>>>>>>>user_id>>>> "+((SysUser) criteria.list().get(0)).getPersonId() +" pwd = "+((SysUser) criteria.list().get(0)).getPassword());
        
        return (SysUser) criteria.list().get(0);
    }
}
