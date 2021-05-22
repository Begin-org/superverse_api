package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.JDBCUtil;
import model.Battle;

public class BattleDaoImplementation implements BattleDao{

	@Override
	public void createBattle(Battle battle) throws Exception {		
		try {
			Connection con = JDBCUtil.getConnection();
			String qry = " INSERT INTO battles (id_winner, id_loser, id_chosen, skill, google_uid_user) " +
						 " VALUES (?,?,?,?,?) ";			
			PreparedStatement pstmt = con.prepareStatement(qry);
			
			int i = 0;
			
			pstmt.setInt(++i, battle.getIdWinner());
			pstmt.setInt(++i, battle.getIdLoser());
			pstmt.setInt(++i, battle.getIdChosen());
			pstmt.setString(++i, battle.getSkill());
			pstmt.setString(++i, battle.getGoogleUidUser());
			
			pstmt.execute();
			
			pstmt = con.prepareStatement(" SELECT id_battle, battle_date FROM battles " +
										 " ORDER BY id_battle DESC " + 
										 " LIMIT 1 ");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				battle.setIdBattle(rs.getInt("id_battle"));
				battle.setBattleDate(rs.getDate("battle_date"));
			}
			
			JDBCUtil.close(con);
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public List<Battle> listBattles(String uidUser) throws Exception {
		List<Battle> listBattles = new ArrayList<Battle>();
		try {
			Connection con = JDBCUtil.getConnection();
			String qry = " SELECT id_battle, id_winner, id_loser, id_chosen, skill, " +
						 " google_uid_user, battle_date FROM battles " +
						 " where google_uid_user = ? ";
			
			PreparedStatement pstmt = con.prepareStatement(qry);
			
			int i = 0;
			
			pstmt.setString(++i, uidUser);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())  { 
				Battle battle = new Battle(
						rs.getInt("id_battle"),
						rs.getInt("id_winner"),
						rs.getInt("id_loser"),
						rs.getInt("id_chosen"),
						rs.getString("skill"),
						rs.getString("google_uid_user"),
						rs.getDate("battle_date"));
				listBattles.add(battle);
			}
			
			JDBCUtil.close(con);
			return listBattles;	
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}			
	}

	@Override
	public Battle getBattle(String uidUser, int idBattle) throws Exception {		
		try {
			Connection con = JDBCUtil.getConnection();
			String qry = "SELECT * FROM battles WHERE google_uid_user = ? and id_battle = ?";			
			PreparedStatement pstmt = con.prepareStatement(qry);
			
			int i = 0;
			
			pstmt.setString(++i, uidUser);
			pstmt.setInt(++i, idBattle);
			
			if(!existsBattle(idBattle, con))
				throw new Exception("Battle does not exists.");
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())  { 
				Battle battle = new Battle(
						rs.getInt("id_battle"),
						rs.getInt("id_winner"),
						rs.getInt("id_loser"),
						rs.getInt("id_chosen"),
						rs.getString("skill"),
						rs.getString("google_uid_user"),
						rs.getDate("battle_date"));

				JDBCUtil.close(con);
				
				return battle;				
			}
			return null;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteBattle(int idBattle) throws Exception{		
		try {
			Connection con = JDBCUtil.getConnection();
			String qry = "DELETE FROM battles WHERE id_battle = ?";			
			PreparedStatement pstmt = con.prepareStatement(qry);
			
			int i = 0;
			
			pstmt.setInt(++i, idBattle);
			
			if(!existsBattle(idBattle, con))
				throw new Exception("Battle does not exists.");
			
			pstmt.execute();

			JDBCUtil.close(con);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	private boolean existsBattle(int idBattle, Connection con) throws Exception {		
		try {
			String qry = "SELECT 1 FROM battles WHERE id_battle = ?";			
			PreparedStatement pstmt = con.prepareStatement(qry);
			
			int i = 0;
			
			pstmt.setInt(++i, idBattle);
			
			ResultSet rs = pstmt.executeQuery();
			
			return rs.next();			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
