# Hexample

The ultimate hexagonal architecture template for java

## Introduction

<p>
Let's begin with stating the obvious: There is no optimal solution for any given problem.<br/>
This is why hexagonal architecture is such a popular design decision.
It allows us to change any given part of the application without even having to touch aspects that do not need change.<br/>
However, this is only the case if your application is designed well, and does not deviate too much from best practices of hexagonal/clean architecture.
</p>

<p>
Since there are multiple valid solutions to the same problem, we see a lot of different implementations across projects trying to achieve the same objectives.<br/>
Some solutions are better than others - some are objectively worse - but most of the time, it's a pretty subjective matter.
</p>

This is why Hexample exists. It aims to achieve the following:

- Reduce the time and discussion it takes to set up new codebase, a template is right here.
- Function as an example for developers when making design decisions (how to keep the domain clean and separated, how
  setup tests, how to
  containerize ...)
- Provide a codebase to experiment with, without having to write too much boilerplate code.

## Project structure

### Vocabulary

### Application layer

The application layer provides everything to make the application work functionally. This includes:

* [The domain](code/application/domain/README.md)
  * Contains the domain model, domain events and ports.
  * Has no dependencies (except for vocabulary, more on this later)
* [Application API](code/application/api)
  * Provides a way to protect the application layer from adapters.
  * This module defines a set of operations the application supports, the data needed to perform these operations and
    the possible outcomes of these operations.
* [Queries](code/application/queries)
  * Implements the application API's read operations.
  * Defines its own read-only ports (archives), this way we have a clear command/query separation. These archives can be
    implemented in the same adapter as the ports in the domain.
* [Use cases](code/application/use-cases)
  * Implements the application API's write operations.
  * Calls the domain to execute business logic, save to ports and publish events.

### [Vocabulary](code/vocabulary)

This module implements the **[ubiquitous language](https://ddd-practitioners.com/home/glossary/ubiquitous-language/)**
by
defining [domain primitives](https://freecontent.manning.com/domain-primitives-what-they-are-and-how-you-can-use-them-to-make-more-secure-software/). <br/>
Should not contain any logic, only value objects and should not have any dependencies.
Use with great care since any module is allowed to depend on this.

### Adapters

The application layer provides ports as interfaces which make it possible to separate technical implementation details
from functional domain logic.
Ports are implemented by adapter modules, the directory structure indicates 2 types of adapters.

* [Driving adapters](code/adapters/driving)
  * Call the application API to execute functional actions.
  * Implement the application API's presenters, so a sensible response to the user can be formed without having to rely
    on exceptions.
* [Driven adapters](code/adapters/driven)
  * Implement ports defined by the application to integrate with infrastructure.
  * In-memory implementations are provided to run the application locally and provide fakes for writing tests.
