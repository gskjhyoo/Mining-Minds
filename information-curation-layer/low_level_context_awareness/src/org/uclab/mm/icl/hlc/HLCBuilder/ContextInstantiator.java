/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.hlc.HLCBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.uclab.mm.icl.hlc.OntologyTools.ContextOntology;
import org.uclab.mm.icl.hlc.OntologyTools.HLCA;
import org.uclab.mm.icl.hlc.OntologyTools.LowLevelContext;
import org.uclab.mm.icl.hlc.OntologyTools.NutritionContext;
import org.uclab.mm.icl.hlc.OntologyTools.PhysicalActivityContext;

import com.hp.hpl.jena.ontology.AllValuesFromRestriction;
import com.hp.hpl.jena.ontology.ComplementClass;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.EnumeratedClass;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.SomeValuesFromRestriction;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * Context Instantiator. Subcomponent of the HLC Builder which generates
 * unclassified HLC instances from concurrent LLC instances.
 * 
 * @author Claudia Villalonga
 * @version 2.0
 * @since 2015-11-06
 */
public class ContextInstantiator {

	/**
	 * Context Ontology which represents the Mining Minds Context Model.
	 */
	private ContextOntology ont;

	/**
	 * Identifier of the Context Instantiator.
	 */
	private int instantiatiorId;

	/**
	 * Counter of the number HLC instances generated by the Context
	 * Instantiator.
	 */
	private long hlcInstNum;

	/**
	 * Constructor of a new Context Instantiator.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 */
	public ContextInstantiator(ContextOntology ont) {

		this.ont = ont;
		Random rand = new Random();
		this.instantiatiorId = rand.nextInt(Integer.MAX_VALUE);
		this.hlcInstNum = 0;

	}

	/**
	 * Method to generate a new HLC Instance due to the start of a LLC.
	 * 
	 * @param llc
	 *            Low Level Context which start is the trigger for the
	 *            generation of the new HLC.
	 * @param listLlcConc
	 *            List of Low Level Context Instances which are concurrent at
	 *            the start time of llc.
	 * @return High Level Context composed of the llc and the listLlcConc, and
	 *         which starts at the start of llc.
	 */
	public PhysicalActivityContext instantiateNewHlcDueToLlcStart(LowLevelContext llc, List<LowLevelContext> listLlcConc) {

		OntModel hlcModel = createHlcModel();
		Individual hlcIndiv = createHlcIndiv(hlcModel);
		setUserForHlc(hlcIndiv, llc);
		setStartTimeForHlcDueToLlcStart(hlcIndiv, llc);

		boolean act = false;
		boolean loc = false;
		boolean emo = false;
		boolean foo = false;  //Asif

//		System.out.println("Context Instantiator Line 95");   //Asif MMV2.5
//		System.out.println(llc.getLlcCategoryName());
		
		switch (llc.getLlcCategoryName()) {

		case HLCA.activityClassName:

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getActivityProp());
			act = true;
			break;

		case HLCA.locationClassName:

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getLocationProp());
			loc = true;
			break;

		case HLCA.emotionClassName:

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getEmotionProp());
			emo = true;
			break;

			
/*			
		case HLCA.foodClassName:      //Asif MMV2.5 Disable When all food Categories are defined

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
*/

		case HLCA.eggClassName:      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;


		case HLCA.fruitClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.grainClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.legumesClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.meatClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.milkdairyClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.nutsClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.seafoodClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.snacksClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.vegClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			
			
		}

		Iterator<LowLevelContext> itConc = listLlcConc.iterator();

		while (itConc.hasNext()) {

			LowLevelContext llcConc = itConc.next();

			switch (llcConc.getLlcCategoryName()) {

			case HLCA.activityClassName:

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getActivityProp());
				act = true;
				break;

			case HLCA.locationClassName:

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getLocationProp());
				loc = true;
				break;

			case HLCA.emotionClassName:

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getEmotionProp());
				emo = true;
				break;

/*			case HLCA.foodClassName:  ///Asif         MMV2.5 Disable When all food Categories are defined

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				*/
				
		
			case HLCA.eggClassName:      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;


			case HLCA.fruitClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.grainClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.legumesClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.meatClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.milkdairyClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.nutsClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.seafoodClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.snacksClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.vegClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				
				
				
				
			}

		}

		if (!act)

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.activityClassName),
					ont.getActivityProp());

		if (!loc)

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.locationClassName),
					ont.getLocationProp());

		if (!emo)

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.emotionClassName),
					ont.getEmotionProp());
		if (!foo)   //Asif

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.foodClassName),
					ont.getFoodProp());
		
		this.hlcInstNum++;

//asif V2.5
//		System.out.println("PhysicalActivityContext Context Instantiator L332: " + hlcModel);

//asif V2.5		

		
		return new PhysicalActivityContext(hlcModel);
	}

	
	
	
	
	
	
	///*************************************************************For NutritionContext instantiateNewNutHlcDueToLlcStart Start   /*****************

	/**
	 * Method to generate a new HLC Instance due to the start of a LLC.
	 * 
	 * @param llc
	 *            Low Level Context which start is the trigger for the
	 *            generation of the new HLC.
	 * @param listLlcConc
	 *            List of Low Level Context Instances which are concurrent at
	 *            the start time of llc.
	 * @return High Level Context composed of the llc and the listLlcConc, and
	 *         which starts at the start of llc.
	 */
	
	public NutritionContext instantiateNewNutHlcDueToLlcStart(LowLevelContext llc, List<LowLevelContext> listLlcConc) {

		OntModel hlcModel = createHlcModel();
		Individual hlcIndiv = createNutHlcIndiv(hlcModel);
		setUserForHlc(hlcIndiv, llc);
		setStartTimeForHlcDueToLlcStart(hlcIndiv, llc);

		boolean act = false;
		boolean loc = false;
		boolean emo = false;
		boolean foo = false;  //Asif

//		System.out.println("Context Instantiator Line 95");   //Asif MMV2.5
//		System.out.println(llc.getLlcCategoryName());
		
		switch (llc.getLlcCategoryName()) {

		case HLCA.activityClassName:

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getActivityProp());
			act = true;
			break;

		case HLCA.locationClassName:

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getLocationProp());
			loc = true;
			break;

		case HLCA.emotionClassName:

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getEmotionProp());
			emo = true;
			break;

			
/*			
		case HLCA.foodClassName:      //Asif MMV2.5 Disable When all food Categories are defined

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
*/

		case HLCA.eggClassName:      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;


		case HLCA.fruitClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.grainClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.legumesClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.meatClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.milkdairyClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.nutsClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.seafoodClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.snacksClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			

		case HLCA.vegClassName :      //Asif MMV2.5

			setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llc, ont.getFoodProp());
			foo = true;
			break;
			
			
		}

		Iterator<LowLevelContext> itConc = listLlcConc.iterator();

		while (itConc.hasNext()) {

			LowLevelContext llcConc = itConc.next();

			switch (llcConc.getLlcCategoryName()) {

			case HLCA.activityClassName:

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getActivityProp());
				act = true;
				break;

			case HLCA.locationClassName:

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getLocationProp());
				loc = true;
				break;

			case HLCA.emotionClassName:

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getEmotionProp());
				emo = true;
				break;

/*			case HLCA.foodClassName:  ///Asif         MMV2.5 Disable When all food Categories are defined

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				*/
				
		
			case HLCA.eggClassName:      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;


			case HLCA.fruitClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.grainClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.legumesClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.meatClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.milkdairyClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.nutsClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.seafoodClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.snacksClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				

			case HLCA.vegClassName :      //Asif MMV2.5

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
				
				
				
				
			}

		}

		if (!act)

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.activityClassName),
					ont.getActivityProp());

		if (!loc)

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.locationClassName),
					ont.getLocationProp());

		if (!emo)

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.emotionClassName),
					ont.getEmotionProp());
		if (!foo)   //Asif

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.foodClassName),
					ont.getFoodProp());
		
		this.hlcInstNum++;

//asif V2.5
//		System.out.println("NutritionContext Context Instantiator L609: " + hlcModel);

//asif V2.5		

		
		return new NutritionContext(hlcModel);
	}
	
	
	
	
	//////**********************************************************For NutritonContext instantiateNewNutHlcDueToLlcStart End /////////////////////////////
	/**
	 * Method to generate a new HLC Instance due to the end of a LLC.
	 * 
	 * @param llc
	 *            Low Level Context which end is the trigger for the generation
	 *            of the new HLC.
	 * @param listLlcConc
	 *            List of Low Level Context Instances which are concurrent at
	 *            the end time of llc.
	 * @return High Level Context composed of the listLlcConc and which starts
	 *         at the end of llc.
	 */
	public PhysicalActivityContext instantiateNewHlcDueToLlcEnd(LowLevelContext llc, List<LowLevelContext> listLlcConc) {

		OntModel hlcModel = createHlcModel();
		Individual hlcIndiv = createHlcIndiv(hlcModel);
		setUserForHlc(hlcIndiv, llc);
		setStartTimeForHlcDueToLlcEnd(hlcIndiv, llc);

		boolean act = false;
		boolean loc = false;
		boolean emo = false;
		boolean foo = false; //Asif

		Iterator<LowLevelContext> itConc = listLlcConc.iterator();

		while (itConc.hasNext()) {

			LowLevelContext llcConc = itConc.next();

			switch (llcConc.getLlcCategoryName()) {

			case HLCA.activityClassName:

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getActivityProp());
				act = true;
				break;

			case HLCA.locationClassName:

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getLocationProp());
				loc = true;
				break;

			case HLCA.emotionClassName:

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getEmotionProp());
				emo = true;
				break;
				
			case HLCA.foodClassName: //Asif

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
			}

		}

		if (!act)

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.activityClassName),
					ont.getActivityProp());

		if (!loc)

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.locationClassName),
					ont.getLocationProp());

		if (!emo)

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.emotionClassName),
					ont.getEmotionProp());

		if (!foo)  //Asif

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.foodClassName),
					ont.getFoodProp());
		
		
		this.hlcInstNum++;

		return new PhysicalActivityContext(hlcModel);

	}

	
	
	
	
	//////**********************************************************For NutritonContext instantiateNewNutHlcDueToLlcEnd Start /////////////////////////////
	
	/**
	 * Method to generate a new HLC Instance due to the end of a LLC.
	 * 
	 * @param llc
	 *            Low Level Context which end is the trigger for the generation
	 *            of the new HLC.
	 * @param listLlcConc
	 *            List of Low Level Context Instances which are concurrent at
	 *            the end time of llc.
	 * @return High Level Context composed of the listLlcConc and which starts
	 *         at the end of llc.
	 */
	public NutritionContext instantiateNewNutHlcDueToLlcEnd(LowLevelContext llc, List<LowLevelContext> listLlcConc) {

		OntModel hlcModel = createHlcModel();
		Individual hlcIndiv = createNutHlcIndiv(hlcModel);
		setUserForHlc(hlcIndiv, llc);
		setStartTimeForHlcDueToLlcEnd(hlcIndiv, llc);

		boolean act = false;
		boolean loc = false;
		boolean emo = false;
		boolean foo = false; //Asif

		Iterator<LowLevelContext> itConc = listLlcConc.iterator();

		while (itConc.hasNext()) {

			LowLevelContext llcConc = itConc.next();

			switch (llcConc.getLlcCategoryName()) {

			case HLCA.activityClassName:

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getActivityProp());
				act = true;
				break;

			case HLCA.locationClassName:

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getLocationProp());
				loc = true;
				break;

			case HLCA.emotionClassName:

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getEmotionProp());
				emo = true;
				break;
				
			case HLCA.foodClassName: //Asif

				setLlcBeingPartOfHlc(hlcModel, hlcIndiv, llcConc, ont.getFoodProp());
				foo = true;
				break;
			}

		}

		if (!act)

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.activityClassName),
					ont.getActivityProp());

		if (!loc)

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.locationClassName),
					ont.getLocationProp());

		if (!emo)

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.emotionClassName),
					ont.getEmotionProp());

		if (!foo)  //Asif

			setNoLlcBeingPartOfHlc(hlcModel, hlcIndiv, ont.getLlcCategoryClass(HLCA.foodClassName),
					ont.getFoodProp());
		
		
		this.hlcInstNum++;

		return new NutritionContext(hlcModel);

	}

	
	
	
	
	
	//////**********************************************************For NutritonContext instantiateNewNutHlcDueToLlcEnd End /////////////////////////////
	
	
	
	/**
	 * Method to create the OntModel associated to a new HLC.
	 * 
	 * @return OntModel associated to a new HLC.
	 */
	private OntModel createHlcModel() {

		OntModel hlcModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		hlcModel.setNsPrefix(HLCA.nsPrefix, HLCA.ns);
		return hlcModel;

	}

	/**
	 * Method to create an individual of the HLC class which represents the new
	 * HLC instance.
	 * 
	 * @param hlcModel
	 *            OntModel associated to the new HLC in which the individual
	 *            will be created.
	 * @return Individual representing the HLC instance.
	 */
	private Individual createHlcIndiv(OntModel hlcModel) {

//		System.out.println("Context INstatiator L832 "+ ont.getHlcClass().getLocalName());      //////MMV2.5
		
		return hlcModel.createIndividual(HLCA.ns + "hlc_" + String.format("%010d", this.instantiatiorId) + "_"
				+ String.format("%019d", this.hlcInstNum), ont.getHlcClass());

	}

	
	
	
	//////**********************************************************For NutritonContext instantiateNewNutHlcDueToLlcEnd Start /////////////////////////////
	

	/**
	 * Method to create an individual of the HLC class which represents the new
	 * HLC instance.
	 * 
	 * @param hlcModel
	 *            OntModel associated to the new HLC in which the individual
	 *            will be created.
	 * @return Individual representing the HLC instance.
	 */
	private Individual createNutHlcIndiv(OntModel hlcModel) {

//		System.out.println("Context INstatiator L832 "+ ont.getHlcClass().getLocalName());     /// MMV2.5
		
		return hlcModel.createIndividual(HLCA.ns + "hlc_" + String.format("%010d", this.instantiatiorId) + "_"
				+ String.format("%019d", this.hlcInstNum), ont.getNutHlcClass());

	}

	
	
	
	//////**********************************************************For NutritonContext instantiateNewNutHlcDueToLlcEnd End /////////////////////////////
	
	
	
	
	
	/**
	 * Method to set the User in the HLC instance, i.e., to assert the value of
	 * the property which links the High Level Context to the User it belongs
	 * to.
	 * 
	 * @param hlcIndiv
	 *            Individual representing the HLC instance.
	 * @param llc
	 *            Low Level Context which triggered the creation of the HLC and
	 *            from which the User should be obtained.
	 */
	private void setUserForHlc(Individual hlcIndiv, LowLevelContext llc) {

		ObjectProperty ctxOfProp = ont.getContextOfProp();
		hlcIndiv.addProperty(ctxOfProp, llc.getObjectPropertyValue(ctxOfProp));

	}

	/**
	 * Method to set the start time of the HLC instance, i.e., to assert the
	 * value of the property which links the High Level Context to its start
	 * time.
	 * 
	 * @param hlcIndiv
	 *            Individual representing the HLC instance.
	 * @param llc
	 *            Low Level Context which start triggered the creation of the
	 *            HLC and from which the start time should be obtained.
	 */
	private void setStartTimeForHlcDueToLlcStart(Individual hlcIndiv, LowLevelContext llc) {

		DatatypeProperty startTimeProp = ont.getStartTimeProp();
		hlcIndiv.addProperty(startTimeProp, llc.getDataPropertyValue(startTimeProp));

	}

	/**
	 * Method to set the start time of the HLC instance, i.e., to assert the
	 * value of the property which links the High Level Context to its start
	 * time.
	 * 
	 * @param hlcIndiv
	 *            Individual representing the HLC instance.
	 * @param llc
	 *            Low Level Context which end triggered the creation of the HLC
	 *            and from which the start time should be obtained.
	 */
	private void setStartTimeForHlcDueToLlcEnd(Individual hlcIndiv, LowLevelContext llc) {

		hlcIndiv.addProperty(ont.getStartTimeProp(), llc.getDataPropertyValue(ont.getEndTimeProp()));

	}

	/**
	 * Method to set in a HLC instance the LLC instance which is part of it.
	 * This means asserting the value of the property which links the High Level
	 * Context to the Low Level Context and asserting the class which states
	 * that this LLC instance is the only value that the property can take. The
	 * type assertion is necessary in order to overcome the Open World
	 * Assumption of OWL.
	 * 
	 * @param hlcModel
	 *            OntModel associated to the HLC instance.
	 * @param hlcIndiv
	 *            Individual representing the HLC instance.
	 * @param llc
	 *            Low Level Context which should be set as part of the HLC.
	 * @param llcProp
	 *            ObjectProperty linking the HLC to the LLC which is part of it.
	 */
	private void setLlcBeingPartOfHlc(OntModel hlcModel, Individual hlcIndiv, LowLevelContext llc,
			ObjectProperty llcProp) {

		Individual llcIndiv = llc.getCtxIndiv();

		hlcIndiv.addProperty(llcProp, llcIndiv);

		EnumeratedClass llcEnumClass = hlcModel.createEnumeratedClass(null, null);
		llcEnumClass.addOneOf(llcIndiv);

		AllValuesFromRestriction llcRestr = hlcModel.createAllValuesFromRestriction(null, llcProp, llcEnumClass);
		hlcIndiv.addOntClass(llcRestr);

		hlcModel.add(llc.getCtxModel());
/*		
		System.out.println(llcIndiv + "  I am here");   ///Asif MMV2.5
		System.out.println(llcProp + "  I am here");   ///Asif MMV2.5
		
Asif MMV2.5*/		
	}

	/**
	 * Method to set in a HLC instance that there is no LLC instance of a given
	 * category, e.g., Activity, Location or Emotion) being part of it. This
	 * type assertion is necessary in order to overcome the Open World
	 * Assumption of OWL.
	 * 
	 * @param hlcModel
	 *            OntModel associated to the HLC instance.
	 * @param hlcIndiv
	 *            Individual representing the HLC instance.
	 * @param llcCategoryClass
	 *            OntClass representing the LLC Category for which there is no
	 *            LLC instance.
	 * @param llcProp
	 *            ObjectProperty linking the HLC to the LLC Category for which
	 *            there is no LLC instance.
	 */
	private void setNoLlcBeingPartOfHlc(OntModel hlcModel, Individual hlcIndiv, OntClass llcCategoryClass,
			ObjectProperty llcProp) {

		SomeValuesFromRestriction llcRestr = hlcModel.createSomeValuesFromRestriction(null, llcProp, llcCategoryClass);

		ComplementClass llcCompl = hlcModel.createComplementClass(null, llcRestr);

		hlcIndiv.addOntClass(llcCompl);

	}

}