
```This repository will remain anonymous until the notification of a paper submitted to ICSE-2023. If you have any questions for the developers, we will give contact information after the notification.```

![example workflow](https://github.com/ameyaKetkar/InferRules/actions/workflows/gradle.yml/badge.svg)

Table of Contents
=================

   * [General info](#general-info)
   * [How to build PyEvolve](#how-to-build-pyevolve)
   * [Research](#research)
      * [How to cite PyEvolve](#research)
   * [API usage guidelines](#api-usage-guidelines)
   * [Running PyEvolve from the command line](#running-pyevolve-from-the-command-line)
   
# General info 

PyEvolve automates the frequently repeated code changes in Python systems. This tool presents a complete pipeline for mining and automating best code evolution practices, ensuring that the your project does not fall behind. The following is a high-level overview of the pipeline.

![h](https://github.com/maldil/PyEvolve/blob/cpatminer/workflow.jpg)


- **Phase 1:** We use [R-CPATMiner](https://github.com/maldil/R-CPATMiner) to mine best practices from version history of Python systems. You can find details on executing R-CPATMiner in its project description. 
- **Phase 2:** We infer the initial transformation rules for the patterns detected by R-CPATMiner.
- **Phase 3:** We identify the potential sites to apply the patterns in the target codes.
- **Phase 4:** We infer final adapted rules to tranplant the identified pattern.

# How to build PyEvolve
To have a fully built PyEvolve, you have to install the following components.
- We use [RulePharser](https://github.com/maldil/RulePharser) to generate an AST for Comby templates that includes both Python and Comby syntaxes. Follow the steps in [RulePharser](https://github.com/maldil/RulePharser) to build it locally and add it to your local maven repository.  
- We use [ComBy](https://comby.dev/docs/get-started#install) as a backend tool to rewrite code. Please follow the steps in their [documentation](https://comby.dev/docs/get-started#install) to install it on your PC.

After completing the above steps, run `./gradlew` build from the root directory to build the project. This will build the project and execute the test cases. If you want to build the project without running the tests, use the command `./gradlew build -x test`.

# API usage guidelines
We will discuss the APIs that can be used for code automation, using the following code example.

The following is a best code evolution practice discovered by [R-CPATMiner](https://github.com/maldil/R-CPATMiner).
```python
res = 0
for elem in elems:
  res = res + elem
``` 
==>
```python
res = np.sum(elems)
```

Our goal is to transplant the above recommended practice to the target code listed below.

```python
def getSum()
  n_diff = 0
  to_eval = getNumber()
  for dif in to_eval.getDiff():
    total = n_diff + dif
    n_diff = total
return n_diff    
```

We will now describe the APIs that can be used for above modification. 

### 1. With pattern and target function as input
```java
MainAdaptor adaptor = new MainAdaptor();
Module codeModule = getPythonModule("/pathToFunction.py"); // pathToFunction is the String value of the file path
Module lpatternModule = getPythonModuleForTemplate("/pathToLPattern.py");// pathToLPattern is the String value of the file path which has LHS of the pattern
Module rpatternModule = getPythonModuleForTemplate("/pathToRPattern.py"); // pathToRFunction is the String value of the file path which has RHS of the pattern
List<stmt> imports = lpatternModule.getInternalBody().stream().filter(x -> x instanceof Import
                || x instanceof ImportFrom).collect(Collectors.toList());
Guards guards = new Guards("/pathToLPattern.py",lpatternModule);
String adaptedCode = adaptor.adaptFunction(imports, guards, lpatternModule, rpatternModule, codeModule); // adaptedCode is the final adapted code
```


# Research
We will add citation information as soon as possible.
# License
All software provided in this repository is subject to the [Apache License Version 2.0](LICENSE).
