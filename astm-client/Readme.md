# Query tube request

# Query response

# Send resuls command

# Examples

## Querying sample tube

```
<STX>
1H|\^&|||A9000P|||||LIS||P|LIS2-A2|<CR>
Q|1|^3788513^#ENTRADA 1^D8||||||||||O<CR>
L|1|N<CR><CR>
<ETX>08<CR><LF>
```

## LIS returning the list of sample tubes

```
<STX>
  1H|\^&||Password|LIS|||||A9000P||P|1|<CR>
  P|1|280655|||^||19000101||||||||||||||||||<CR>
  O|1|1217804||^^^VELO|R|20200715070100|20200715070100||||N||||||||||||||Q<CR>
  L|1|F|<CR>
<ETX>A0<CR><LF>
```


## Sending results

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


# Install instructions for sample server

```
pip3 install astm
```

