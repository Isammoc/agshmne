package io.github.algorys.agshmne.start;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import io.github.algorys.agshmne.character.player.Player;
import io.github.algorys.agshmne.character.player.skills.SkillType;
import io.github.algorys.agshmne.game.JGame;
import io.github.algorys.agshmne.map.region.RandomRegionFactory;
import io.github.algorys.agshmne.map.region.Region;
import io.github.algorys.agshmne.tile.JTile;

@SuppressWarnings("serial")
public class JFicheNav extends JPanel {
	
	public static enum Step {
		SOCIAL, CARAC, COMPETENCES, EQUIPMENT, RESUME, CONFIRMATION
	}

	private CardLayout cl;
	private Step step;
	private Action next;
	private Action previous;
	private JPanel jpPrincipal;
	private JSocialPanel jpSocial;
	private JCaracPanel jpCarac;
	private JCompPanel jpComp;
	private JInvPanel jpInv;
	private JConfirmPanel jpConfirm;
	
	private JFrame parent;

	public JFicheNav(JFrame jframe) {
		this.setBackground(Color.BLACK);
		this.parent = jframe;
		// Next Abstract Action
		next = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JFicheNav.this.step == Step.CONFIRMATION) {
					int res = JOptionPane.showOptionDialog(JFicheNav.this, 
							"Êtes-vous sûr(e) de vouloir jouer avec " + jpSocial.getPjName() + " ?", 
							"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, "Oui");
					if(res == JOptionPane.YES_OPTION) {
						Region region = new RandomRegionFactory().create();
						Player pj = new Player(region);
						// Init Social 
						pj.getSocial().setName(jpSocial.getPjName());
						pj.getSocial().setSexe(jpSocial.getPjSexe());
						pj.getSocial().setRace(jpSocial.getPjRace());
						pj.getSocial().setClasse(jpSocial.getPjClass());
						pj.getSocial().setBackground(jpSocial.getPjBackground());
						// Init Carac
						pj.getAttributes().setFOR(jpCarac.getFOR());
						pj.getAttributes().setDEX(jpCarac.getDEX());
						pj.getAttributes().setCON(jpCarac.getCON());
						pj.getAttributes().setINT(jpCarac.getINT());
						pj.getAttributes().setCHA(jpCarac.getCHA());
						pj.initVital();
						// Init Skills
						pj.getSkills().setSkillLevel(SkillType.cuisine, jpComp.getCuisiner());
						pj.getSkills().setSkillLevel(SkillType.fouiller, jpComp.getFouiller());
						pj.getSkills().setSkillLevel(SkillType.boucherie, jpComp.getBoucherie());
						pj.getSkills().setSkillLevel(SkillType.bucheron, jpComp.getBucheron());
						pj.getSkills().setSkillLevel(SkillType.miner, jpComp.getMiner());
						pj.getSkills().setSkillLevel(SkillType.cultiver, jpComp.getCultiver());
						pj.getSkills().setSkillLevel(SkillType.pecher, jpComp.getPecher());
						pj.getSkills().setSkillLevel(SkillType.magie, jpComp.getMagie());
						// Init Inventaire
						pj.getInventory().addItem(jpInv.getFirstObject());
						pj.getInventory().addItem(jpInv.getSecondObject());
						// Entrée dans le jeu
						JGame game = new JGame(pj);
						game.setVisible(true);
						parent.dispose();
					}
				} else {
					JFicheNav.this.next();
				}
			}
		};
		
		// Previous Abstract Action
		previous = new AbstractAction("Précédent") {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JFicheNav.this.step == Step.SOCIAL) {
					System.out.println("Retour au menu principal");
				} else {
					JFicheNav.this.previous();
				}
			}
		};

		// Layout
		this.setLayout(new BorderLayout());
		cl = new CardLayout();
		
		// Panel Principal
		jpPrincipal = new JPanel(cl);
		jpPrincipal.setOpaque(false);
		this.add(jpPrincipal, BorderLayout.CENTER);

		// SOCIAL
		jpSocial = new JSocialPanel();
		jpSocial.setOpaque(false);
		jpPrincipal.add(jpSocial, Step.SOCIAL.name());

		// CARACTERISTIQUES
		jpCarac = new JCaracPanel();
		jpCarac.setOpaque(false);
		jpPrincipal.add(jpCarac, Step.CARAC.name());
		
		// COMPETENCES
		jpComp = new JCompPanel();
		jpComp.setOpaque(false);
		jpPrincipal.add(jpComp, Step.COMPETENCES.name());
		
		// EQUIPEMENT
		jpInv = new JInvPanel();
		jpInv.setOpaque(false);
		jpPrincipal.add(jpInv, Step.EQUIPMENT.name());

		cl.show(jpPrincipal, Step.SOCIAL.name());

		JPanel jpButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jpButton.setOpaque(false);
		jpButton.add(new JButton(previous));
		jpButton.add(new JButton(next));
		this.add(jpButton, BorderLayout.SOUTH);

		// Initialisation à Social
		this.setStep(Step.SOCIAL);
	}

	public Action getNextAction() {
		return next;
	}

	public Action getPreviousAction() {
		return previous;
	}

	private void setStep(Step step) {
		this.step = step;
		if (this.step == Step.CONFIRMATION) {
			jpConfirm = new JConfirmPanel(jpSocial, jpCarac, jpComp, jpInv);
			jpConfirm.setOpaque(false);
			jpPrincipal.add(jpConfirm, Step.CONFIRMATION.name());
			this.next.putValue(Action.NAME, "Valider");
		} else {
			this.next.putValue(Action.NAME, "Suivant");
		}

		if (this.step == Step.SOCIAL) {
			this.previous.setEnabled(false);
		} else {
			this.previous.setEnabled(true);
		}

		cl.show(jpPrincipal, this.step.name());
	}

	public void next() {
		switch (this.step) {
		case SOCIAL:
			this.setStep(Step.CARAC);
			break;
		case CARAC:
			this.setStep(Step.COMPETENCES);
			break;
		case COMPETENCES:
			this.setStep(Step.EQUIPMENT);
			break;	
		case EQUIPMENT:
			this.setStep(Step.CONFIRMATION);
			break;
		default:
			throw new IllegalArgumentException("Pas de next !");
		}
	}

	public void previous() {
		switch (this.step) {
		case CARAC:
			this.setStep(Step.SOCIAL);
			break;
		case COMPETENCES:
			this.setStep(Step.CARAC);
			break;
		case EQUIPMENT:
			this.setStep(Step.COMPETENCES);
			break;
		case CONFIRMATION:
			this.setStep(Step.EQUIPMENT);
			break;
		default:
			throw new IllegalArgumentException("Pas de next !");
		}
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension preferredSize = super.getPreferredSize();
		preferredSize.width += 150;
		preferredSize.height += 300;
		return preferredSize;
	}

	@Override
	public Dimension getMinimumSize() {
		return this.getPreferredSize();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getPreferredSize().width;
		int height = getPreferredSize().height;
		int x = (this.getWidth() - width) / 2;
		int y = (this.getHeight() - height) / 2;
		try {
			Image img = ImageIO.read(JTile.class.getClassLoader().getResource("parchemin.png"));
			Image imgBack = ImageIO.read(JTile.class.getClassLoader().getResource("table.png") );
			g.drawImage(imgBack, 0, 0, this.getWidth(), this.getHeight(), this);
			g.drawImage(img, x, y, width, height, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
