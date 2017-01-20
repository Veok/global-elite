package web;

import dao.RepositoryCatalog;
import dao.repositories.IRepositoryCatalog;
import domain.model.Team;
import hdao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author L on 09.01.2017.
 */
@WebServlet("/TeamServlet")
public class TeamServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "jdbc:hsqldb:hsql://localhost/workdb";
        try {
            IRepositoryCatalog catalog = new RepositoryCatalog(url);
            //TODO refactor to HibernateUtil
            SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            Session session1 = sessionFactory.openSession();
            String name = req.getParameter("name");
            Team team = getTeamByName(name);
            if (team!=null) {
                HttpSession session = req.getSession();
               session.setAttribute(SessionKey.teamStats, team.getTeamStatistics());
                session.setAttribute(SessionKey.team, team);
                resp.sendRedirect("team.jsp");
            } else {
                resp.getWriter().println("Brak danej drużyny w bazie");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    //TODO refactor to TeamService
    public Team getTeamByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = null;
        Team team = null;

        try {
            SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            Session session1 = sessionFactory.openSession();
            session1.getTransaction();
            org.hibernate.query.Query query = session1.createQuery("from Team where name='" + name +"'");
            team = (Team) query.uniqueResult();
            session1.getTransaction().commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return team;
    }

    public List<Team> getListOfTeam() {
        List<Team> list = new ArrayList<Team>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = null;
        try {
            tx.begin();
            list = session.createQuery("from Team").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

}
