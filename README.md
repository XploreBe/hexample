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
- Function as an example for developers when making design decisions (how to keep the domain clean and separated, how setup tests, how to
  containerize ...)
- To be a repository of knowledge acquired by professional experience.

## The Domain

It's not easy to come up with a domain for an example application.
It should allow for some complexity so good examples can be created, but readers should not have difficulties understanding the domain.
For this reason, computer hardware and PC building was chosen as the core domain.
Every developer has knowledge of at least the basic concepts, and plenty have built their own gaming station.
