## Query tube request


* function(sampleId, rackId, holeId, senderId=A9000P, receiverId=LIS)

```
H|\^&|||$senderId|||||$receiverId||P|LIS2-A2|
Q|1|^$sampleId^$rackId^$holeID||||||||||O
L|1|N<CR><CR>
```


## Query response

```
1H|\^&|||LIS|||||A9000P||P|1|
P|1|$patientId|||$patientLastName^$patientFirstName||$bithDate|$sex|||||$phisician||||||||||||
O|1|$specimenId||^^^$test1\^^^$test2\^^^$test3|R||||||N||||||||||||||Q
L|1|F|
```

In the P record type, we extract also the following values:

* index 12: Custom field 1
* index 15: Custom field 2
* index 16: Custom field 3
* index 17: Custom field 4
* index 18: Custom field 5
* index 19: Custom field 6
* index 21: Custom field 7
* index 21: Custom field 8
* index 22: Custom field 0
* index 23: Custom field 10
* index 26: Extraction center 
* index 33: Service name
* index 34: Section



## Send results command

```
1H|\^&|||A9000P|||||LIS||P|LIS2-A2|
P|1|$patientId|||^^||19000101||||||||||||||||||
O|2|$sampleID^$rackId^$holeId||^^^PRIMARY_T\^^^test1|R||||||||||||||||||||F
R|3|^^^PRIMARY_T^^^^|#VELOCIDADES 1_A3|||||SUCCESS||||20200715122300
R|4|^^^test1^^^^|OK|||||F||||20200715122300
L|1|N
```

The P record is only sent if the aqualink service has not been rebooted and therefore lost the volatile PII data.

A O record type is sent for the primary tube specifying:

* specimenId = primary tube sample id
* primary tube output rack id
* primary tube output rack hole label
* a list of the tests being sent (using *PRIMARY_T* for the primary tube destination).

There is a R record that stores the primary tube destination (PRIMARY_T). In index 1 (starting at 0), there is the location (rack_hole) and in index 6, SUCCESS or FAILURE for the primary tube. Finally the date is also added.

For the rest of real tests requested, the R record type is also used with flags=F, test name equal to ^^^testname^^^and the status OK or ERROR. 

Finally for secondary tubes, also a R result type is used, with name ^^^SECONDARY_T_$index^^^  where index is the aliquote index. The status represents in this case the output rackId_holeId. 
 

## Examples

### Querying sample tube

```
<STX>
1H|\^&|||A9000P|||||LIS||P|LIS2-A2|<CR>
Q|1|^3788513^#ENTRADA 1^D8||||||||||O<CR>
L|1|N<CR><CR>
<ETX>08<CR><LF>
```

### LIS returning the list of sample tubes

```
<STX>
  1H|\^&||Password|LIS|||||A9000P||P|1|<CR>
  P|1|280655|||^||19000101||||||||||||||||||<CR>
  O|1|1217804||^^^VELO|R|20200715070100|20200715070100||||N||||||||||||||Q<CR>
  L|1|F|<CR>
<ETX>A0<CR><LF>
```


### Sending results

```
<STX>
  1H|\^&|||A9000P|||||LIS||P|LIS2-A2|<CR>
  P|1|342008|||^^||19000101||||||||||||||||||<CR>
  O|2|3788512^#VELOCIDADES 1^A3||^^^PRIMARY_T\^^^VELO|R||||||||||||||||||||F<CR>
  R|3|^^^PRIMARY_T^^^^|#VELOCIDADES 1_A3|||||SUCCESS||||20200715122300<CR>
  R|4|^^^VELO^^^^|O
<ETB>68<CR><LF>
<STX>
  2K|||||F||||20200715122300<CR>
  L|1|N<CR><CR>
<ETX>C5<CR><LF>
```


## Install instructions for sample server

```
pip3 install astm
```

