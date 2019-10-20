# Technical Challenge

This challenge consists of two questions - a programming problem and a written exercise. Submit your solutions by committing them into this git repository.

### Challenge #1
You are given a list of integers, which may be both positive and negative. Each integer in the list must either be paired with another element in the list or be a single element. Once the elements have been paired, the integers in the pairs are multiplied and the results are summed up - the sum will include the single elements.

Write a program to find the biggest possible sum.

###### Examples:
- For the list `[0,1,2,3,4,5]` the pairs `(4,5)` and `(2,3)` are formed and `0` and `1` are single elements. The max sum is `27`: `(20+6+0+1)`.

- For the list `[-1,0,1]` the pair `(-1,0)` is formed and `1` is a single element. The max sum is `1`.

- For the list `[1,1]` no pairs are formed -- only two single elements. The max sum is `2`.

You are free to choose the implementation language and how input/output is handled.

### Challenge #2
Imagine your company has created a service which can translate small snippets of text (say, up to 140 characters long) from one language to another (e.g., from English to Danish). The translation is computationally expensive, and your backend systems are having difficulty keeping up with the growing popularity of the service.

To reduce load on the backend systems, you are tasked with designing a cache to store commonly-translated snippets. Assume it is not an option to use existing caching systems.

Describe how your caching system would handle the following operations (for example, what algorithms/data structures would you use, and why):

* a) Locate the translation of a given snippet, if it is present in the cache
* b) Determine which snippet should be removed from the cache to make room for new translations. This should be the snippet which has been in the cache for the longest time without having had a 'hit'
