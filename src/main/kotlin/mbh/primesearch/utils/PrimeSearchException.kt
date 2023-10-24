package mbh.primesearch.utils

class SearchingStartedException : Exception("The searching is already started.")
class InvalidRangeException : Exception("The range is invalid. Please give correct min/max values.")
class PrimeCountExceededException : Exception("The range is too large. Please select a smaller range.")
class RangeBoundExceededException : Exception("The searching doesn't reach this range yet.")
class ThreadCountExceededException : Exception("Too many threads. Please give correct thread count.")
