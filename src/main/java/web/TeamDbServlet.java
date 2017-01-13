package web;

import dao.RepositoryCatalog;
import dao.repositories.IRepositoryCatalog;
import domain.model.Player;
import domain.model.PlayerStatistics;
import domain.model.Team;
import domain.model.TeamStatistics;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author L on 13.01.2017.
 */
@WebServlet("/TeamDbServlet")
public class TeamDbServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "jdbc:hsqldb:hsql://localhost/workdb";


        try{

            IRepositoryCatalog catalog = new RepositoryCatalog(url);
            HttpSession session = req.getSession();
            Team team = (Team) session.getAttribute("team");
            TeamStatistics teamStatistics = new TeamStatistics();
            Player player = (Player) session.getAttribute("player");
            catalog.teamsStats().add(teamStatistics);
           // catalog.teams().get(catalog.players().getName(player.getNick()).getId()).setName(team.getName());
            //catalog.teams().get(catalog.players().getName(player.getNick()).getId()).setName(team.getCountry());
            catalog.teams().add(team);
            player.setTeam(team);
            catalog.saveAndClose();
            resp.sendRedirect("profile.jsp");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}