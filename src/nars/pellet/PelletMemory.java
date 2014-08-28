/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nars.pellet;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import nars.core.NAR;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

/**
 *
 * TODO extract this into a MemoryObserver event-handler for Memory that gets called when:
 *  --new concept created
 *  --new question task created
 *  --cycle elapsed
 *  etc..
 */
public class PelletMemory  {
    public final PelletReasoner reasoner;
    private final NAR nar;
    
    public PelletMemory(NAR n, PelletReasoner reasoner) {       
        this.nar = n;
        this.reasoner = reasoner;
    }

    void loadOntologyAxioms() {
        for (OWLAxiom a : reasoner.getRootOntology().getAxioms()) {
            addAxiom(a);
        }                
    }

    void loadReasonerAxioms() {
        
        for (OWLAxiom a : reasoner.getRootOntology().getAxioms()) {
            addAxiom(a);
        }                
    }
    
    private void addAxiom(OWLAxiom a) {
        
        //System.out.println(a.getClass() + " " + a);
        //class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#BodyDuct> <http://www.co-ode.org/ontologies/galen#BodyTube>)
        
        if (a instanceof OWLSubClassOfAxiom) {
            OWLSubClassOfAxiom sc = (OWLSubClassOfAxiom)a;
            {
                String subclass = sc.getSubClass().toString();
                String superclass = sc.getSuperClass().toString();
                
                String subclassname = subclass.substring(1, subclass.length()-1);
                String superclassname = superclass.substring(1, superclass.length()-1);

                String st = "<\"" + subclassname + "\" --> \"" + superclassname + "\">.";
                System.out.println(st);
                nar.addInput(st);
            }
        }
    }
    
    
    
    
}

/*
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Forearm>))
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#ArterialWall> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#BodyWall> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isLayerOf> <http://www.co-ode.org/ontologies/galen#Artery>)) )
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#SalmonellaGroupL> <http://www.co-ode.org/ontologies/galen#Salmonella>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#ChemicalProcess>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Calculus> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasPathologicalStatus> <http://www.co-ode.org/ontologies/galen#pathological>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#stable>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#ReflexMovement> <http://www.co-ode.org/ontologies/galen#InvoluntaryMovement>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubObjectPropertyOfAxiomImpl SubObjectPropertyOf(<http://www.co-ode.org/ontologies/galen#hasProximalDistalSelector> <http://www.co-ode.org/ontologies/galen#hasPositionalSelector>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#ChemicalPathway> <http://www.co-ode.org/ontologies/galen#ProcessFeature>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#isFrequencyOf>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Shaver> <http://www.co-ode.org/ontologies/galen#CuttingTool>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Pancreas> ObjectIntersectionOf(ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isServedBy> <http://www.co-ode.org/ontologies/galen#AnteriorInferiorPancreaticoduodenalArtery>) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isServedBy> <http://www.co-ode.org/ontologies/galen#AnteriorSuperiorPancreaticoduodenalArtery>) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isServedBy> <http://www.co-ode.org/ontologies/galen#CaudalPancreaticArtery>) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isServedBy> <http://www.co-ode.org/ontologies/galen#CommonInferiorPancreaticoduodenalArtery>) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isServedBy> <http://www.co-ode.org/ontologies/galen#DorsalPancreaticArtery>) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isServedBy> <http://www.co-ode.org/ontologies/galen#GreatPancreaticArtery>) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isServedBy> <http://www.co-ode.org/ontologies/galen#PosteriorInferiorPancreaticoduodenalArtery>) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isServedBy> <http://www.co-ode.org/ontologies/galen#PosteriorSuperiorPancreaticoduodenalArtery>)))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Discharge> <http://www.co-ode.org/ontologies/galen#Transport>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#PoplitealVein> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isPairedOrUnpaired> <http://www.co-ode.org/ontologies/galen#mirrorImaged>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubObjectPropertyOfAxiomImpl SubObjectPropertyOf(<http://www.co-ode.org/ontologies/galen#hasTopology> <http://www.co-ode.org/ontologies/galen#StructuralAppearanceModifier>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#InverseCollectionAttribute>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Extension>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#BrucellaAbortus> <http://www.co-ode.org/ontologies/galen#Brucella>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Brain>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#partial> <http://www.co-ode.org/ontologies/galen#CompletenessState>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Ileum> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isLinearDivisionOf> <http://www.co-ode.org/ontologies/galen#LowerGastrointestinalTract>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Talus> <http://www.co-ode.org/ontologies/galen#TarsalBone>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#CryptococcusNeoformans>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#isLateralPositionOf>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Pulling>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Screw>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#CatheterSpecimenOfUrine>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#topologicallySolid> <http://www.co-ode.org/ontologies/galen#TopologyState>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubObjectPropertyOfAxiomImpl SubObjectPropertyOf(<http://www.co-ode.org/ontologies/galen#isGravidityOf> <http://www.co-ode.org/ontologies/galen#InverseOrganismModifier>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Celsius>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#ClostridiumFallax>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#StriatedMuscleContractionProcess>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#AnastomoticBranch>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#InferiorVenaCava>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#HelicobacterPylori>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#hasQuality>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#presence> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isExistenceOf> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#BodySubstance> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasPathologicalStatus> <http://www.co-ode.org/ontologies/galen#pathological>)))) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasAbnormalityStatus> <http://www.co-ode.org/ontologies/galen#nonNormal>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Actinomyces> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasShape> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Shape> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasState> <http://www.co-ode.org/ontologies/galen#cylindrical>))))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Horn> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isSpecificSolidDivisionOf> <http://www.co-ode.org/ontologies/galen#MeniscusOfKneeJoint>)) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isPairedOrUnpaired> <http://www.co-ode.org/ontologies/galen#atLeastPaired>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Leg> <http://www.co-ode.org/ontologies/galen#ExtremityLongPart>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Forehead>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#absence> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isExistenceOf> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#BodyProcess> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasIntrinsicAbnormalityStatus> <http://www.co-ode.org/ontologies/galen#nonNormal>)))) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasAbnormalityStatus> <http://www.co-ode.org/ontologies/galen#normal>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#InterphalangealJoint>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#ConcentrationUnit> <http://www.co-ode.org/ontologies/galen#CompositeUnit>)
class uk.ac.manchester.cs.owl.owlapi.OWLFunctionalObjectPropertyAxiomImpl FunctionalObjectProperty(<http://www.co-ode.org/ontologies/galen#hasDuration>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Sulphadimidine>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubObjectPropertyOfAxiomImpl SubObjectPropertyOf(<http://www.co-ode.org/ontologies/galen#TemporalModifierAttribute> <http://www.co-ode.org/ontologies/galen#hasFeature>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#SurfaceVisibilityStatus> <http://www.co-ode.org/ontologies/galen#VisibilityStatus>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#pleomorphic> <http://www.co-ode.org/ontologies/galen#CellMorphologyState>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#ElbowJoint> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isStructuralComponentOf> <http://www.co-ode.org/ontologies/galen#Elbow>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#isSpecificallyServedBy>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#isActedOnSpecificallyBy>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubObjectPropertyOfAxiomImpl SubObjectPropertyOf(<http://www.co-ode.org/ontologies/galen#InverseSpecificationLevelAttribute> <http://www.co-ode.org/ontologies/galen#InverseDomainAttribute>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#PhysicalProtectionRole> <http://www.co-ode.org/ontologies/galen#StructuralRole>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#AxillaryVein> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isBranchOf> <http://www.co-ode.org/ontologies/galen#SubclavianVein>))
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#PlantarRegionOfFoot> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#SurfaceRegion> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasAnteriorPosteriorSelector> <http://www.co-ode.org/ontologies/galen#anterior>) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isSurfaceDivisionOf> <http://www.co-ode.org/ontologies/galen#Foot>)) )
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#BacterialSensitivityTest> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#LaboratoryInvestigation> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isToDetermine> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Sensitivity> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isSensitivityOf> <http://www.co-ode.org/ontologies/galen#Bacterium>) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isWithReferenceTo> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#presence> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isPresenceAbsenceOf> <http://www.co-ode.org/ontologies/galen#Antimicrobial>)))))) )
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Larynx> <http://www.co-ode.org/ontologies/galen#InternalOrgan>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#ClostridiumWelchii>))
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#IschaemicMyocardium> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#IschaemicLesion> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasLocation> <http://www.co-ode.org/ontologies/galen#Myocardium>)) )
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#increasedActivityLevel>))
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#Macrocyte> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Erythrocyte> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasSize> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Size> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasExpectedLevelState> <http://www.co-ode.org/ontologies/galen#elevatedLevel>)))) )
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#BodyWall> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#BodyStructure> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#playsPhysiologicalRole> <http://www.co-ode.org/ontologies/galen#PhysicalBarrierRole>)) )
class uk.ac.manchester.cs.owl.owlapi.OWLSubObjectPropertyOfAxiomImpl SubObjectPropertyOf(<http://www.co-ode.org/ontologies/galen#LocativeAttribute> <http://www.co-ode.org/ontologies/galen#ConstructiveAttribute>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#PerforatingLesion> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasTopology> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Topology> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasState> <http://www.co-ode.org/ontologies/galen#tubular>))))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Ligating> <http://www.co-ode.org/ontologies/galen#ClosingProcedure>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#presence> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isExistenceOf> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#BodyProcess> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasPathologicalStatus> <http://www.co-ode.org/ontologies/galen#physiological>)))) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasAbnormalityStatus> <http://www.co-ode.org/ontologies/galen#normal>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Titantium> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#ElementalChemical> <http://www.co-ode.org/ontologies/galen#Metal>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#AbsorptionPhotometer>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Maxilla>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Knee> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isSolidDivisionOf> <http://www.co-ode.org/ontologies/galen#LowerExtremity>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#UltrasoundMachine> <http://www.co-ode.org/ontologies/galen#ImagingDevice>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#lightHue> <http://www.co-ode.org/ontologies/galen#ColourHueState>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#BodyTemperature>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Liver>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#ProximalDistalChangeInPositionState> <http://www.co-ode.org/ontologies/galen#ChangeInPositionState>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#liquidCrystal> <http://www.co-ode.org/ontologies/galen#PhysicalStateState>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Valve> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasSpecificFunction> <http://www.co-ode.org/ontologies/galen#ControlOfFlow>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#HeartWall>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#never>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#StreptococcusFaecalis> <http://www.co-ode.org/ontologies/galen#Streptococcus>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#LevelOfConsciousness>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#CommonIliacVein> <http://www.co-ode.org/ontologies/galen#NAMEDVein>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#NAMEDGITractBodyPart>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Spore>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Ventricle> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasTopology> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Topology> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasState> <http://www.co-ode.org/ontologies/galen#actuallyHollow>))))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#medial> <http://www.co-ode.org/ontologies/galen#MedialLateralPositionState>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Introitus> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasAlphaConnection> <http://www.co-ode.org/ontologies/galen#Vagina>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#AreaValue>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Transplanting>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#hasConsequence>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Hemiballismus> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasPathologicalStatus> <http://www.co-ode.org/ontologies/galen#pathological>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#ColicFlexure> <http://www.co-ode.org/ontologies/galen#NAMEDGITractBodyPart>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#NAMEDSensoryPart> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasTopology> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Topology> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasState> <http://www.co-ode.org/ontologies/galen#topologicallySolid>))))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#VisualInspectingProcedure>))
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#Valvotomy> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Incising> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#actsOn> <http://www.co-ode.org/ontologies/galen#Valve>)) )
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Oligodendrocyte> <http://www.co-ode.org/ontologies/galen#Neurone>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#BloodSugarConcentration>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#AnteriorHornOfLateralMensicus>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#isAssociatedWith>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#CardiacInsufficiencyFollowingCardiacSurgery>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubObjectPropertyOfAxiomImpl SubObjectPropertyOf(<http://www.co-ode.org/ontologies/galen#isInSuspensionWithin> <http://www.co-ode.org/ontologies/galen#isMixedThroughout>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Pseudomonas> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isActedOnBy> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Gramstaining> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasEffectiveness> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Effectiveness> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasState> <http://www.co-ode.org/ontologies/galen#ineffective>))))))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Pisiform>))
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#HypertrophyProcess> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Growth> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasOutcome> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#BodyStructure> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isLocationOf> <http://www.co-ode.org/ontologies/galen#HypertrophicLesion>)))) )
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#FemoralJointSurfaces>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#State> <http://www.co-ode.org/ontologies/galen#Aspect>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#RickettsiaRickettsi> <http://www.co-ode.org/ontologies/galen#Rickettsiae>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubObjectPropertyOfAxiomImpl SubObjectPropertyOf(<http://www.co-ode.org/ontologies/galen#isCauseOf> <http://www.co-ode.org/ontologies/galen#IsCausallyLinkedTo>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#GramStainedBacterialCell>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#RightColicArtery>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#GenericBodyStructure> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isStructuralComponentOf> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#BodyPart> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isPairedOrUnpaired> <http://www.co-ode.org/ontologies/galen#mirrorImaged>)))) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isPairedOrUnpaired> <http://www.co-ode.org/ontologies/galen#mirrorImaged>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#PatientPositioningProcedure> <http://www.co-ode.org/ontologies/galen#PreparationProcedure>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Topology>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#IntestinalTract>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Tumour>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#InverseDiseaseProcessAttribute>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#semicircular>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#ExactlyPairedBodyStructure>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#PlateletCount> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasAbsoluteState> <http://www.co-ode.org/ontologies/galen#lowLevel>)) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasPathologicalStatus> <http://www.co-ode.org/ontologies/galen#pathological>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Humerus>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#isAtOtherEndOf>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#InflammatoryLesion> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isOutcomeOf> <http://www.co-ode.org/ontologies/galen#InflammatoryProcess>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#midline> <http://www.co-ode.org/ontologies/galen#LateralityPositionState>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#actuallyHollow>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Aspect> <http://www.co-ode.org/ontologies/galen#ModifierConcept>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Artery>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubObjectPropertyOfAxiomImpl SubObjectPropertyOf(<http://www.co-ode.org/ontologies/galen#isSpecificallyToDetermine> <http://www.co-ode.org/ontologies/galen#isToDetermine>)
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#ScarringOfPapillaryMuscle> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Scar> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasSpecificLocation> <http://www.co-ode.org/ontologies/galen#PapillaryMuscle>)) )
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#MycobacteriumSmegmatis> <http://www.co-ode.org/ontologies/galen#Mycobacterium>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#absence> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isExistenceOf> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#MentalProcess> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasIntrinsicAbnormalityStatus> <http://www.co-ode.org/ontologies/galen#nonNormal>)))) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasPathologicalStatus> <http://www.co-ode.org/ontologies/galen#physiological>))
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#SecondaryHypertension> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Hypertension> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#IsCausallyLinkedTo> <http://www.co-ode.org/ontologies/galen#PathologicalCondition>)) )
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Endocrinology>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Iron>))
class uk.ac.manchester.cs.owl.owlapi.OWLTransitiveObjectPropertyAxiomImpl TransitiveObjectProperty(<http://www.co-ode.org/ontologies/galen#AnatomicalLocativeAttribute>)
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#solidState> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#PhysicalState> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasState> <http://www.co-ode.org/ontologies/galen#solid>)) )
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#SensitivityState> <http://www.co-ode.org/ontologies/galen#ProcessState>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#isAnteriorPosteriorDiplacementOf>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#MyocardialDegeneration>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubObjectPropertyOfAxiomImpl SubObjectPropertyOf(<http://www.co-ode.org/ontologies/galen#InverseOrganismModifier> <http://www.co-ode.org/ontologies/galen#isFeatureOf>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#hasDuration>))
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#RaisedSerumFructosamineConcentration> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#SerumFructosamineConcentration> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasAbsoluteState> <http://www.co-ode.org/ontologies/galen#highLevel>)) )
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Bacteroides> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasStructuralComponent> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#BacterialCell> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasShape> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Shape> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasState> <http://www.co-ode.org/ontologies/galen#cylindrical>))))))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Coccyx>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#RightLateralPosition> <http://www.co-ode.org/ontologies/galen#onSide>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubObjectPropertyOfAxiomImpl SubObjectPropertyOf(<http://www.co-ode.org/ontologies/galen#hasPhysicalState> <http://www.co-ode.org/ontologies/galen#SubstanceModifierAttribute>)
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#Oliguria> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Micturition> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasProcessActivityLevel> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#ProcessActivityLevel> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasExpectedLevelState> <http://www.co-ode.org/ontologies/galen#depressedLevel>)))) )
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#SigmoidArteries> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isBranchOf> <http://www.co-ode.org/ontologies/galen#InferiorMesentericArtery>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Capsule>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#theWhole> <http://www.co-ode.org/ontologies/galen#Ratio>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#AxillaryArtery> <http://www.co-ode.org/ontologies/galen#NAMEDArtery>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#BodyStructure> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasLeftRightSelector> <http://www.co-ode.org/ontologies/galen#LeftRightState>) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isPairedOrUnpaired> <http://www.co-ode.org/ontologies/galen#leftRightPaired>)) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasSpecificationLevel> <http://www.co-ode.org/ontologies/galen#atLeastWellSpecified>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Shaft> <http://www.co-ode.org/ontologies/galen#NAMEDSolidBoneDivisions>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Ligating>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Horn> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasAnteriorPosteriorSelector> <http://www.co-ode.org/ontologies/galen#anterior>) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isSpecificSolidDivisionOf> <http://www.co-ode.org/ontologies/galen#MedialMeniscus>)) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isPairedOrUnpaired> <http://www.co-ode.org/ontologies/galen#mirrorImaged>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#hasPhysicalMeans>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#AnastomoticLoops> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isPairedOrUnpaired> <http://www.co-ode.org/ontologies/galen#unpaired>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Irradiation>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Murein> <http://www.co-ode.org/ontologies/galen#ComplexChemicals>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Perforation>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#KneeJointCavity> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasBlindPouchDivision> <http://www.co-ode.org/ontologies/galen#SupraPatellarPouch>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#HaemoglobinConcentrationProcedure>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Escherichia>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Hilum> <http://www.co-ode.org/ontologies/galen#GenericInternalStructure>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#EntamoebaHistolytica>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#ColourHueState> <http://www.co-ode.org/ontologies/galen#ColourState>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Neisseria> <http://www.co-ode.org/ontologies/galen#Bacterium>)
class uk.ac.manchester.cs.owl.owlapi.OWLFunctionalObjectPropertyAxiomImpl FunctionalObjectProperty(<http://www.co-ode.org/ontologies/galen#isAtOtherEndOf>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#LeftInferiorPhrenicArtery> <http://www.co-ode.org/ontologies/galen#NAMEDArtery>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#NAMEDPathologicalProcess>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Teenager>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#hasLinearDivision>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#StomaForming> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#CreatingProcedure> <http://www.co-ode.org/ontologies/galen#OpeningProcedure>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#ChemicalState> <http://www.co-ode.org/ontologies/galen#SubstanceState>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Celsius> <http://www.co-ode.org/ontologies/galen#TemperatureUnit>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Room>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Neonate> <http://www.co-ode.org/ontologies/galen#Baby>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Borrelia>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#quarterCircular> <http://www.co-ode.org/ontologies/galen#circular>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#hasMixedThroughout>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#micrograms> <http://www.co-ode.org/ontologies/galen#MassUnit>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#PatelloFemoralJoint>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#SaturationColourState> <http://www.co-ode.org/ontologies/galen#ColourState>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Lavaging> <http://www.co-ode.org/ontologies/galen#CleaningProcedure>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Staphylococcus> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasFunction> <http://www.co-ode.org/ontologies/galen#FacultativeAnaerobicMetabolicProcess>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Stripping>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Bacterium>))
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#RaisedEosinophilCount> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#EosinophilCount> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasAbsoluteState> <http://www.co-ode.org/ontologies/galen#highLevel>)) )
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#ChangeInLevelState> <http://www.co-ode.org/ontologies/galen#LevelState>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#CoronaryAtherosclerosis>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Hypertension> <http://www.co-ode.org/ontologies/galen#SystemicDisease>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Enterobacter>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Finger> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasSpecificationLevel> <http://www.co-ode.org/ontologies/galen#atLeastWellSpecified>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Vulva>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#nanometres>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#StreptococcusUberis>))
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#CephalosporinSensitivity> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#sensitive> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isStateOf> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#Sensitivity> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isWithReferenceTo> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#presence> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isPresenceAbsenceOf> <http://www.co-ode.org/ontologies/galen#Cephalosporin>)))))) )
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Vulva> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isPairedOrUnpaired> <http://www.co-ode.org/ontologies/galen#unpaired>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Hook> <http://www.co-ode.org/ontologies/galen#HoldingTool>)
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Microcyte>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#EnterobacterGergoviae>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#MassUnit>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#anterior>))
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#coma> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#LevelOfConsciousness> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasAbsoluteState> <http://www.co-ode.org/ontologies/galen#undetectedLevel>)) )
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#CardiacSurgery>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Facilitation>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Nipple> <http://www.co-ode.org/ontologies/galen#NAMEDTrunkBodyPart>)
class uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl EquivalentClasses(<http://www.co-ode.org/ontologies/galen#Pyridoxine> <http://www.co-ode.org/ontologies/galen#VitaminB6> )
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(Class(<http://www.co-ode.org/ontologies/galen#Methicillin>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#ReproductiveProcess> <http://www.co-ode.org/ontologies/galen#Process>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Pressure> <http://www.co-ode.org/ontologies/galen#PhysicalFeature>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Femur> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasSpecificSolidDivision> <http://www.co-ode.org/ontologies/galen#LesserTrochanter>))
class uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/galen#isUrgencyOf>))
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#BodyDuct> <http://www.co-ode.org/ontologies/galen#BodyTube>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(<http://www.co-ode.org/ontologies/galen#Back> <http://www.co-ode.org/ontologies/galen#NAMEDTrunkBodyPart>)
class uk.ac.manchester.cs.owl.owlapi.OWLSubClassOfAxiomImpl SubClassOf(ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#BodyStructure> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#isOutcomeOf> ObjectIntersectionOf(<http://www.co-ode.org/ontologies/galen#BodyProcess> ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasIntrinsicPathologicalStatus> <http://www.co-ode.org/ontologies/galen#pathological>)))) ObjectSomeValuesFrom(<http://www.co-ode.org/ontologies/galen#hasIntrinsicPathologicalStatus> <http://www.co-ode.org/ontologies/galen#pathological>))
BUILD SUCCESSFUL (total time: 17 seconds)

*/
