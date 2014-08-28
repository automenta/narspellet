/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nars.pellet;

import aterm.ATermAppl;
import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.vocabulary.RDFS;
import java.util.Iterator;
import java.util.Set;
import nars.core.NAR;
import nars.core.build.DefaultNARBuilder;
import nars.io.TextOutput;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

/**
 *
 * @author me
 */
public class TestPellet {
    private final OWLOntologyManager manager;
    private final PelletReasoner reasoner;

    public TestPellet() throws OWLOntologyCreationException {

        String file = "file:data/pellet/people+pets.owl";

        System.out.print("Reading file " + file + "...");
        manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(IRI.create(file));

        reasoner = PelletReasonerFactory.getInstance().createReasoner(ontology);
        System.out.println("done.");

        
        
        reasoner.getKB().realize();
        //reasoner.getKB().printClassTree();
        System.out.println(reasoner.getKB().getRules());

        //NARS----------------------
        NAR pnar = new DefaultNARBuilder().build();
        new TextOutput(pnar, System.out);
        
        PelletMemory pm = new PelletMemory(pnar, reasoner);
        pm.loadOntologyAxioms();
        pnar.finish(1000);

    }
    
    public static void usageWithDefaultModel() {
        System.out.println("Results with plain RDF Model");
        System.out.println("----------------------------");
        System.out.println();
        
        // ontology that will be used
        String ont = "http://protege.cim3.net/file/pub/ontologies/koala/koala.owl#";
        String ns = "http://protege.stanford.edu/plugins/owl/owl-library/koala.owl#";
        
  	    // create Pellet reasoner
        Reasoner reasoner = org.mindswap.pellet.jena.PelletReasonerFactory.theInstance().create();
        
        // create an empty model
        Model emptyModel = ModelFactory.createDefaultModel( );
        
        // create an inferencing model using Pellet reasoner
        InfModel model = ModelFactory.createInfModel( reasoner, emptyModel );
            
        // read the file
        model.read( ont );
        
        // print validation report
        ValidityReport report = model.validate();
        printIterator( report.getReports(), "Validation Results" );
        
        // print superclasses
        Resource c = model.getResource( ns + "MaleStudentWith3Daughters" );         
        printIterator(model.listObjectsOfProperty(c, RDFS.subClassOf), "All super classes of " + c.getLocalName());
        
        System.out.println();
    }

    public static void usageWithOntModel() {    
        System.out.println("Results with OntModel");
        System.out.println("---------------------");
        System.out.println();

        // ontology that will be used
        String ont = "http://protege.cim3.net/file/pub/ontologies/koala/koala.owl#";
        String ns = "http://protege.stanford.edu/plugins/owl/owl-library/koala.owl#";
        
        // create an empty ontology model using Pellet spec
        OntModel model = ModelFactory.createOntologyModel( org.mindswap.pellet.jena.PelletReasonerFactory.THE_SPEC );
            
        // read the file
        model.read( ont );
        
        // print validation report
        ValidityReport report = model.validate();
        printIterator( report.getReports(), "Validation Results" );
        
        // print superclasses using the utility function
        OntClass c = model.getOntClass( ns + "MaleStudentWith3Daughters" );         
        printIterator(c.listSuperClasses(), "All super classes of " + c.getLocalName());
        // OntClass provides function to print *only* the direct subclasses 
        printIterator(c.listSuperClasses(true), "Direct superclasses of " + c.getLocalName());
        
        System.out.println();
    }
    
    public static void printIterator(Iterator<?> i, String header) {
        System.out.println(header);
        for(int c = 0; c < header.length(); c++)
            System.out.print("=");
        System.out.println();
        
        if(i.hasNext()) {
	        while (i.hasNext()) 
	            System.out.println( i.next() );
        }       
        else
            System.out.println("<EMPTY>");
        
        System.out.println();
    }
    
    protected void extrastuff() {
        
//        // create property and resources to query the reasoner
//        OWLClass Person = manager.getOWLDataFactory().getOWLClass(IRI.create("http://xmlns.com/foaf/0.1/Person"));
//        OWLObjectProperty workHomepage = manager.getOWLDataFactory().getOWLObjectProperty(IRI.create("http://xmlns.com/foaf/0.1/workInfoHomepage"));
//        OWLDataProperty foafName = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://xmlns.com/foaf/0.1/name"));
        OWLClass thing = manager.getOWLDataFactory().getOWLThing();
        
        
        
        
        // get all instances of Person class
        Set<ATermAppl> individuals = reasoner.getKB().getIndividuals();
        for (ATermAppl sameInd : individuals) {
            // sameInd contains information about the individual (and all other individuals that were inferred to be the same)
            //OWLNamedIndividual ind = sameInd.getRepresentativeElement();
            System.out.println(sameInd);

            /*
            // get the info about this specific individual
            //Set<OWLLiteral> names = reasoner. getDataPropertyValues(ind, foafName);
            NodeSet<OWLClass> types = reasoner.getTypes(ind, true);
            //NodeSet<OWLNamedIndividual> homepages = reasoner.getObjectPropertyValues( ind, workHomepage );

            // we know there is a single name for each person so we can get that value directly
            //String name = names.iterator().next().getLiteral();
            //System.out.println("Name: " + name);

            // at least one direct type is guaranteed to exist for each individual 
            OWLClass type = types.iterator().next().getRepresentativeElement();
            System.out.println("Type:" + type);
            */
//            // there may be zero or more homepages so check first if there are any found
//                if( homepages.isEmpty() ) {
//                        System.out.print( "Homepage: Unknown" );
//                }
//                else {
//                        System.out.print( "Homepage:" );
//                        for( Node<OWLNamedIndividual> homepage : homepages ) {
//                                System.out.print( " " + homepage.getRepresentativeElement().getIRI() );
//                        }
//                }
            System.out.println();
            System.out.println();
        }
        
    }

    public static void main(String[] args) throws OWLOntologyCreationException {
        new TestPellet();
    }
}
