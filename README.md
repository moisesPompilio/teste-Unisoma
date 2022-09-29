<div align="center" id="top"> 
  <img src="https://i.ytimg.com/vi/m_l-8lEjYk8/maxresdefault.jpg" alt="Unisoma" />

  &#xa0;

  <!-- <a href="https://unisoma.netlify.app">Demo</a> -->
</div>

<h1 align="center">Unisoma</h1>

<p align="center">
  <img alt="Github top language" src="https://img.shields.io/github/languages/top/moisesPompilio/teste-Unisoma?color=56BEB8">

  <img alt="Github language count" src="https://img.shields.io/github/languages/count/moisesPompilio/teste-Unisoma?color=56BEB8">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/moisesPompilio/teste-Unisoma?color=56BEB8">

  <img alt="License" src="https://img.shields.io/github/license/moisesPompilio/teste-Unisoma?color=56BEB8">

  <!-- <img alt="Github issues" src="https://img.shields.io/github/issues/moisesPompilio/teste-Unisoma?color=56BEB8" /> -->

  <!-- <img alt="Github forks" src="https://img.shields.io/github/forks/moisesPompilio/teste-Unisoma?color=56BEB8" /> -->

  <!-- <img alt="Github stars" src="https://img.shields.io/github/stars/moisesPompilio/teste-Unisoma?color=56BEB8" /> -->
</p>

<!-- Status -->

<!-- <h4 align="center"> 
	🚧  Unisoma 🚀 Under construction...  🚧
</h4> 

<hr> -->

<p align="center">
  <a href="#dart-about">About</a> &#xa0; | &#xa0; 
  <a href="#sparkles-features">Features</a> &#xa0; | &#xa0;
  <a href="#rocket-technologies">Technologies</a> &#xa0; | &#xa0;
  <a href="#white_check_mark-requirements">Requirements</a> &#xa0; | &#xa0;
  <a href="#checkered_flag-starting">Starting</a> &#xa0; | &#xa0;
  <a href="#memo-license">License</a> &#xa0; | &#xa0;
  <a href="https://github.com/moisesPompilio" target="_blank">Author</a>
</p>

<br>

## :dart: About ##

The project is a challenge proposed by the company Unisoma, which presents two exercises. The first exercise is to make a RESTFULL API, which makes it possible to save an employee with the following Name, CPF, Date of Birth, Telephone, Address and Salary and also make the salary readjustment according to the table passed by the company. The second challenge is to build a RESTFULL API that calculates the income tax from the API information of the first challenge.

routes:
  Exercise 1:

    Find All:
      PATH:
       -http://localhost:8081/api/funcionario
     Method:
       - GET
     Describe:
       - route responsible for returning all employees

    Save:
      PATH:
       -http://localhost:8081/api/funcionario
     Method:
       - POST
     Describe:
       - route responsible for registering an employee

    Update:
      PATH:
        -http://localhost:8081/api/funcionario/{CPF}
      Method:
        - PUT
      Describe:
        - route responsible for making changes to the Employee's information by the CPF, must replace {CPF} of the PATH by the employee's CPF

    Find by CPF:
      PATH:
        -http://localhost:8081/api/funcionario/{CPF}
      Method:
       - GET
      Describe:
       - route responsible for returning the employee by the CPF, must replace {CPF} of the PATH by the CPF of the employee

    Delete:
      PATH:
        -http://localhost:8081/api/funcionario/{CPF}
      Method:
        - DELETE
      Describe:
        - route responsible for deleting an Employee by the CPF, must replace {CPF} of the PATH by the CPF of the employee

    Salary readjustment:
      PATH:
         -http://localhost:8081/api/reajusteSalario/{CPF}
      Method:
        - POST
      Describe:
        - route responsible for readjusting the employee's salary, must replace {CPF} of the PATH by the CPF of the employee

  Exercio 2:\
    Imposto de Renda:\
      PATH:\
         -http://localhost:8082/api/reajusteSalario/{CPF}\
      Method:\
        - GET
      Describe:\
        - rota responsavel por calcular o imposto de renda do funcionario, must replace {CPF} of the PATH by the CPF of the employee


Comments:
- the PATH are based on the docker configuration, that is, if you change the application ports in docker compose or try to run the project in a way other than docker, the ports may vary.
- The application has CPF and Telephone validation, the data must be entered with the ratings and cannot contain letters and spaces (Example: CPF - XXX.XXX.XXX-XX - telephone - (XX)XXXX-XXXX ).
- Salary cannot be less than zero.


## :sparkles: Features ##

:heavy_check_mark: build  entity Funcionario ;\
:heavy_check_mark: build  DTO Funcionario and ReajusteSalario (Exercio 1);\
:heavy_check_mark: build  Repository Funcionario (Exercio 1);\
:heavy_check_mark: build  Controller Funcionario and ReajusteSalario (Exercio 1);\
:heavy_check_mark: build  Test FuncionarioServices and ReajusteSalarioServices (Exercio 1);\
:heavy_check_mark: build  Test FuncionarioController and ReajusteSalarioController (Exercio 1);\
:heavy_check_mark: build  Test FuncionarioController and ReajusteSalarioController (Exercio 1);\
:heavy_check_mark: Dockerize  Exercio 1;\
:heavy_check_mark: build  DTO ImpostoDeRenda (Exercio 2);\
:heavy_check_mark: build  Controller ImpostoDeRenda (Exercio 2);\
:heavy_check_mark: build  Test ImpostoDeRendaServices (Exercio 2);\
:heavy_check_mark: build  Test ImpostoDeRendaController (Exercio 2);\
:heavy_check_mark: build  Test ImpostoDeRendaController (Exercio 2);\
:heavy_check_mark: Dockerize  Exercio 2;\


## :rocket: Technologies ##

The following tools were used in this project:

- [Java](https://www.java.com/)
- [Maven](https://maven.apache.org/)
- [Sprig](https://spring.io/)
- [Mock](https://site.mockito.org/)
- [JUnit](https://junit.org/)
- [PostgreSQL](https://www.postgresql.org/)

## :white_check_mark: Requirements ##

Before starting :checkered_flag:, you need to have [Git](https://git-scm.com), [Maven](https://maven.apache.org/), [Java](https://www.java.com/) and [Docker](https://www.docker.com/) installed.

## :checkered_flag: Starting ##

```bash
# Clone this project
$ git clone https://github.com/moisesPompilio/teste-Unisoma

# Access
$ cd teste-Unisoma

# Run the projects
$ docker-compose up

# The API of the Funcionarios will initialize in the <http://localhost:8081>
# The API of the Imposto-Renda will initialize in the <http://localhost:8082>
```

## :memo: License ##

This project is under license from MIT. For more details, see the [LICENSE](LICENSE.md) file.


Made with :heart: by <a href="https://github.com/moisesPompilio" target="_blank">Moises Pompilio</a>

&#xa0;

<a href="#top">Back to top</a>
