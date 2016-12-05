package dao.repositories;

import domain.model.MatchHistory;
import domain.model.MatchScoreBoard;

import java.util.List;

/**
 * @author L on 17.11.2016.
 */
public interface IMatchHistoryRepository extends IRepository<MatchHistory> {

    public List<MatchHistory> withScores(MatchScoreBoard matchScoreBoard);


}