# Current commit status:
[![CircleCI](https://circleci.com/gh/gra-m/gd-pet-clinic/tree/main.svg?style=svg)](https://circleci.com/gh/gra-m/gd-pet-clinic/tree/main)

# Spring 5 Spring Guru Pet Clinic
Following the course, updating where needed but not coding along to video. Once a
concept is known I pause the video and create tests/ code myself. Only copy pasting
template.html.

Org: Gra-m

By: John Thompson/ originally Pivotal 

On: 14/03/2022
### What was new for me? 
* First Spring project with more than one module
* Testing Controllers with MockMvc
* Displaying all template bodies inside a main layout template (th)
* Centralising ID creation in BaseEntityClass -> AbstractMapService -> ConcreteMapServices
* Creating a MapService with full CRUD
* Using Generics within an AbstractMapService
* Using Generic CrudService<T,Id> that all other service Interfaces extend
* Using Lombok Builders
* Using ModelAndView, BindingResult
* Creating a multi-action endpoint based on the contents of an object (processFindForm)
* Injecting/Using a reassigned parameter, a submitted object in:
* public String processFindForm(Owner owner, BindingResult result, Model model) [owner]
* @ModelAttribute: 
  * binds a method parameter or method return value to a named model attribute, and then exposes it to a web view.
    * [@ControllerAdvice](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ControllerAdvice.html)
* Exception Handling:
  * @ResponseStatus custom exception -> which HTTP status is thrown globally
  * @ExceptionHandler -> @Controller level



### Project resources used or useful

[Markdown Cheat Sheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet "Adam P")

[Stack Edit](https://stackedit.io "31/08")

[Dillinger](https://dillinger.io "until you are off of visible page..")

[PossInterestingRefactorExercise](https://github.com/Celebes/spring-boot-pet-clinic/blob/master/pet-clinic-data/src/main/java/guru/springframework/sfgpetclinic/services/jpa/AbstractJpaService.java)


Table of Contents:

1. [Exception Handling](#1)
2. [Heading 2](#2)

   a. [Sub Heading a](#2a)
3. [Heading 3](#3)
4. [Heading 4](#4)

<a id="1"></a>
Exception Handling:
* @ResponseStatus custom exception -> which HTTP status is thrown globally
* @ExceptionHandler -> @Controller level
* Interface: HandlerExceptionResolver
  * ExceptionHandlerExceptionResolver -> matches uncaught exceptions to @ExceptionHandler
  * ResponseStatusExceptionResolver -> looks for uncaught exceptions matching @ResponseStatus
  * DefaultHandlerExceptionResolver -> (Internal to SpringMvc) converts standard Spring exceptions to HTTP status codes
* Just HTTP status == @ResponseStatus
* Redirection to view needed = SimpleMappingExceptionResolver
* both? == @ExceptionHandler on controller

<a id="2"></a>
Heading 2:

<a id="2a"></a>
SubHeading 2a:

<a id="3"></a>
Heading 3:

<a id="4"></a>
Heading 4:
    