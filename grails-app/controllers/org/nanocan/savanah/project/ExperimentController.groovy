package org.nanocan.savanah.project

import grails.plugins.springsecurity.Secured
import org.nanocan.project.Experiment
import org.nanocan.project.Project
import org.nanocan.savanah.library.Library
import org.nanocan.security.Person

@Secured(['ROLE_USER'])
class ExperimentController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def scaffold = Experiment


    def index() {}

    def experimentFromLibrary(){
        if(request.getMethod() == 'POST'){
            println("Yay!")
        }

        // TODO: Make scaffolding work with Project (gives error on dates/persons)
        if(Project.all.size() == 0){
            def me = Person.findByUsername("mdissing")

            Project p = new Project(
                    projectTitle: "FirstTest",
                    projectDescription: "Bla blah blah",
                    createdBy: me,
                    lastUpdatedBy: me,
                    dateCreated: new Date(),
                    lastUpdated: new Date()
            )
            p.save(failOnError: true)

            Project p2 = new Project(
                    projectTitle: "SecondTest",
                    projectDescription: "Bla blah blah2",
                    createdBy: me,
                    lastUpdatedBy: me,
                    dateCreated: new Date(),
                    lastUpdated: new Date()
            )
            p2.save(failOnError: true)
        }
        [projectList:Project.all, libraryList:Library.all]
    }


}
