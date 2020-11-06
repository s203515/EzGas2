# Project Estimation  

Authors: Davide Battaglia, Andrea Sergio Ferraris, Lorenzo Lanari, Vittorio Pellittieri

Date: 2020-06-14

Version: 1.0 

# Contents

- [Estimate by product decomposition]
- [Estimate by activity decomposition ]

# Estimation approach

<Consider the EZGas  project as described in YOUR requirement document, assume that you are going to develop the project INDEPENDENT of the deadlines of the course>

# Estimate by product decomposition

###

|             | Estimate                        | 
| ----------- | ------------------------------- | 
| NC =  Estimated number of classes to be developed | 30 | 
|  A = Estimated average size per class, in LOC | 60 |
| S = Estimated size of project, in LOC (= NC * A) | 1800 |
| E = Estimated effort, in person hours (here use productivity 10 LOC per person hour)  | 180 |
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro) | 5400 |
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) | 10 |

# Estimate by activity decomposition

###

|  Activity name | Estimated effort (person hours) | 
| ----------- | ------------------------------- |
| Requirements | 30 |
| V&V Requirements | 10 |
| Design | 30 |
| V&V Design | 10 |
| Implementation | 45 |
| Test, code inspection | 55 |

###

```plantuml
@startgantt
project starts the 2020/04/01
[Requirements] lasts 14 days
[Requirements] is colored in Coral

[V&V Requirements] starts at [Requirements]'s end
[V&V Requirements] lasts 3 days
[V&V Requirements] is colored in ForestGreen

[Design] starts at [V&V Requirements]'s end
[Design] lasts 14 days
[Design] is colored in Gray
 
[V&V Design] starts at [Design]'s end
[V&V Design] lasts 3 days
[V&V Design] is colored in GreenYellow/Green

[Implementation] starts at [V&V Design]'s end
[Implementation] lasts 21 days
[Implementation] is colored in LightSkyBlue

[Test, code inspection] starts at [Implementation]'s end
[Test, code inspection] lasts 21 days
[Test, code inspection] is colored in Yellow
@endgantt
```
