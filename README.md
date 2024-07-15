> [!WARNING]
> The repo is not maintained, if you'd like to take over maintainance contact me at Johannes.Laier@gmail.com

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# Simple Java SEPA XML Generator

The Single Euro Payments Area (SEPA) is a payment-integration initiative of the European Union for simplification of bank transfers denominated in euro. As of 2018, SEPA consists of the 28 member states of the European Union, the four member states of the European Free Trade Association (Iceland, Liechtenstein, Norway and Switzerland), Andorra, Monaco and San Marino.

This Java library proviedes SEPA XML files to automate direct debit and credit transfer payment processes. For direct debit transactions you need a creaditor identifier the central bank of your countty is providing to you. The following standarts are supported: pain.001.002.03.xsd pain.008.002.02.xsd

## How to use?

#### SEPA Direct Debit

```java

import org.simple.sepa.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExampleDirectDebit {
    public static void main(String[] args) {
        final String creditorID = "DE98ZZZ09999999999";

        final SEPABankAccount reciver = new SEPABankAccount(
                "DE89370400440532013000",
                "DEUTDEBBXXX",
                "Joe Doe"
        );

        final List<SEPATransaction> transactions = new ArrayList<SEPATransaction>() {{
            add(new SEPATransaction(
                            new SEPABankAccount(
                                    "DE05500105173195282731",
                                    "VBRSDE33347",
                                    "Peter Parker"
                            ),
                            10.20d,
                            "Invoice 4711564",
                            new Date(),
                            "MYCOMP11111111",
                            new Date(),
                            SEPATransaction.Currency.EUR
                    )
            );

            add(new SEPATransaction(
                            new SEPABankAccount(
                                    "DK5250511963137134",
                                    "UINVDEFFXXX",
                                    "Carl White"
                            ),
                            1000.00d,
                            "Invoice 789765",
                            new Date(),
                            "MYCOMP22222222",
                            new Date(),
                            SEPATransaction.Currency.EUR
                    )
            );

            add(new SEPATransaction(
                            new SEPABankAccount(
                                    "CZ7627005991764514418145",
                                    "SWBSDESSXXX",
                                    "Frank Black"
                            ),
                            50.00d,
                            "Invoice 7856",
                            new Date(),
                            "MYCOMP11111111",
                            new Date(),
                            SEPATransaction.Currency.EUR
                    )
            );
        }};

        final SEPA sepa = new SEPADirectDebit(reciver, transactions, creditorID);
        System.out.println(sepa.toString());
    }
}
```

#### SEPA Credit Transfer


```java

import org.simple.sepa.SEPA;
import org.simple.sepa.SEPABankAccount;
import org.simple.sepa.SEPACreditTransfer;
import org.simple.sepa.SEPATransaction;

import java.util.ArrayList;
import java.util.List;

public class ExampleCreditTransfer {
    public static void main(String[] args) {
        final SEPABankAccount sender = new SEPABankAccount(
                "DE89370400440532013000",
                "DEUTDEBBXXX",
                "Joe Doe"
        );

        final List<SEPATransaction> transactions = new ArrayList<SEPATransaction>() {{
            add(new SEPATransaction(
                            new SEPABankAccount(
                                    "DE05500105173195282731",
                                    "VBRSDE33347",
                                    "Peter Parker"
                            ),
                            2000.20d,
                            "Salary PP 2018-07",
                            SEPATransaction.Currency.EUR
                    )
            );

            add(new SEPATransaction(
                            new SEPABankAccount(
                                    "DK5250511963137134",
                                    "UINVDEFFXXX",
                                    "Carl White"
                            ),
                            1000.00d,
                            "Salary CW 2015-12",
                            SEPATransaction.Currency.EUR
                    )
            );

            add(new SEPATransaction(
                            new SEPABankAccount(
                                    "CZ7627005991764514418145",
                                    "SWBSDESSXXX",
                                    "Frank Black"
                            ),
                            5000.00d,
                            "Salary FB 2017-05",
                            SEPATransaction.Currency.EUR
                    )
            );
        }};

        final SEPA sepa = new SEPACreditTransfer(sender, transactions);
        sepa.write(System.out);
    }
}
```

#### XML File Result for SEPA Direct Debit

```xml
      
<?xml version="1.0" encoding="UTF-8"?>
<Document xmlns="urn:iso:std:iso:20022:tech:xsd:pain.008.002.02" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="urn:iso:std:iso:20022:tech:xsd:pain.008.002.02 pain.008.002.02.xsd">
   <CstmrDrctDbtInitn>
      <GrpHdr>
         <MsgId>DEUTDEBBXXX0020181403121442</MsgId>
         <CreDtTm>2018-07-03T12:14:42.000Z</CreDtTm>
         <NbOfTxs>3</NbOfTxs>
         <InitgPty>
            <Nm>Joe Doe</Nm>
         </InitgPty>
      </GrpHdr>
      <PmtInf>
         <PmtInfId>PMT-ID0-20181403121442</PmtInfId>
         <PmtMtd>DD</PmtMtd>
         <BtchBookg>true</BtchBookg>
         <NbOfTxs>3</NbOfTxs>
         <CtrlSum>1060.2</CtrlSum>
         <PmtTpInf>
            <SvcLvl>
               <Cd>SEPA</Cd>
            </SvcLvl>
            <LclInstrm>
               <Cd>CORE</Cd>
            </LclInstrm>
            <SeqTp>
               <Cd>FRST</Cd>
            </SeqTp>
         </PmtTpInf>
         <ReqdColltnDt>2018-07-03</ReqdColltnDt>
         <Cdtr>
            <Nm>Joe Doe</Nm>
         </Cdtr>
         <CdtrAcct>
            <Id>
               <IBAN>DE89370400440532013000</IBAN>
            </Id>
         </CdtrAcct>
         <CdtrAgt>
            <FinInstnId>
               <BIC>DEUTDEBBXXX</BIC>
            </FinInstnId>
         </CdtrAgt>
         <ChrgBr>SLEV</ChrgBr>
         <CdtrSchmeId>
            <Id>
               <PrvtId>
                  <Othr>
                     <Id>DE98ZZZ09999999999</Id>
                     <SchmeNm>
                        <Prtry>SEPA</Prtry>
                     </SchmeNm>
                  </Othr>
               </PrvtId>
            </Id>
         </CdtrSchmeId>
         <DrctDbtTxInf>
            <PmtId>
               <EndToEndId>NOTPROVIDED</EndToEndId>
            </PmtId>
            <Amt>
               <InstdAmt Ccy="EUR">10.2</InstdAmt>
            </Amt>
            <DrctDbtTx>
               <MndtRltdInf>
                  <MndtId>MYCOMP11111111</MndtId>
                  <DtOfSgntr>2018-07-03</DtOfSgntr>
                  <AmdmntInd>false</AmdmntInd>
               </MndtRltdInf>
            </DrctDbtTx>
            <DbtrAgt>
               <FinInstnId>
                  <BIC>VBRSDE33347</BIC>
               </FinInstnId>
            </DbtrAgt>
            <Dbtr>
               <Nm>Peter Parker</Nm>
            </Dbtr>
            <DbtrAcct>
               <Id>
                  <IBAN>DE05500105173195282731</IBAN>
               </Id>
            </DbtrAcct>
            <UltmtDbtr>
               <Nm>Peter Parker</Nm>
            </UltmtDbtr>
            <RmtInf>
               <Ustrd>Invoice 4711564</Ustrd>
            </RmtInf>
         </DrctDbtTxInf>
         <DrctDbtTxInf>
            <PmtId>
               <EndToEndId>NOTPROVIDED</EndToEndId>
            </PmtId>
            <Amt>
               <InstdAmt Ccy="EUR">1000.0</InstdAmt>
            </Amt>
            <DrctDbtTx>
               <MndtRltdInf>
                  <MndtId>MYCOMP22222222</MndtId>
                  <DtOfSgntr>2018-07-03</DtOfSgntr>
                  <AmdmntInd>false</AmdmntInd>
               </MndtRltdInf>
            </DrctDbtTx>
            <DbtrAgt>
               <FinInstnId>
                  <BIC>UINVDEFFXXX</BIC>
               </FinInstnId>
            </DbtrAgt>
            <Dbtr>
               <Nm>Carl White</Nm>
            </Dbtr>
            <DbtrAcct>
               <Id>
                  <IBAN>DK5250511963137134</IBAN>
               </Id>
            </DbtrAcct>
            <UltmtDbtr>
               <Nm>Carl White</Nm>
            </UltmtDbtr>
            <RmtInf>
               <Ustrd>Invoice 789765</Ustrd>
            </RmtInf>
         </DrctDbtTxInf>
         <DrctDbtTxInf>
            <PmtId>
               <EndToEndId>NOTPROVIDED</EndToEndId>
            </PmtId>
            <Amt>
               <InstdAmt Ccy="EUR">50.0</InstdAmt>
            </Amt>
            <DrctDbtTx>
               <MndtRltdInf>
                  <MndtId>MYCOMP11111111</MndtId>
                  <DtOfSgntr>2018-07-03</DtOfSgntr>
                  <AmdmntInd>false</AmdmntInd>
               </MndtRltdInf>
            </DrctDbtTx>
            <DbtrAgt>
               <FinInstnId>
                  <BIC>SWBSDESSXXX</BIC>
               </FinInstnId>
            </DbtrAgt>
            <Dbtr>
               <Nm>Frank Black</Nm>
            </Dbtr>
            <DbtrAcct>
               <Id>
                  <IBAN>CZ7627005991764514418145</IBAN>
               </Id>
            </DbtrAcct>
            <UltmtDbtr>
               <Nm>Frank Black</Nm>
            </UltmtDbtr>
            <RmtInf>
               <Ustrd>Invoice 7856</Ustrd>
            </RmtInf>
         </DrctDbtTxInf>
      </PmtInf>
   </CstmrDrctDbtInitn>
</Document>

  
```

#### XML File Result for SEPA Credit Transfer

```xml

<?xml version="1.0" encoding="UTF-8"?>
<Document xmlns="urn:iso:std:iso:20022:tech:xsd:pain.008.002.02" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="urn:iso:std:iso:20022:tech:xsd:pain.008.002.02 pain.008.002.02.xsd">
   <CstmrDrctDbtInitn>
      <GrpHdr>
         <MsgId>DEUTDEBBXXX0020183502113507</MsgId>
         <CreDtTm>2018-07-02T11:35:07.000Z</CreDtTm>
         <NbOfTxs>3</NbOfTxs>
         <InitgPty>
            <Nm>Joe Doe</Nm>
         </InitgPty>
      </GrpHdr>
      <PmtInf>
         <PmtInfId>PMT-ID0-20183502113507</PmtInfId>
         <PmtMtd>DD</PmtMtd>
         <BtchBookg>true</BtchBookg>
         <NbOfTxs>3</NbOfTxs>
         <CtrlSum>8000.2</CtrlSum>
         <PmtTpInf>
            <SvcLvl>
               <Cd>SEPA</Cd>
            </SvcLvl>
            <LclInstrm>
               <Cd>CORE</Cd>
            </LclInstrm>
            <SeqTp>
               <Cd>FRST</Cd>
            </SeqTp>
         </PmtTpInf>
         <ReqdColltnDt>2018-07-02</ReqdColltnDt>
         <Dbtr>
            <Nm>Joe Doe</Nm>
         </Dbtr>
         <DbtrAcct>
            <Id>
               <IBAN>DE89370400440532013000</IBAN>
            </Id>
         </DbtrAcct>
         <DbtrAgt>
            <FinInstnId>
               <BIC>DEUTDEBBXXX</BIC>
            </FinInstnId>
         </DbtrAgt>
         <ChrgBr>SLEV</ChrgBr>
         <CdtTrfTxInf>
            <PmtId>
               <EndToEndId>NOTPROVIDED</EndToEndId>
            </PmtId>
            <Amt>
               <InstdAmt Ccy="EUR">2000.2</InstdAmt>
            </Amt>
            <CdtrAgt>
               <FinInstnId>
                  <BIC>VBRSDE33347</BIC>
               </FinInstnId>
            </CdtrAgt>
            <Cdtr>
               <Nm>Peter Parker</Nm>
            </Cdtr>
            <CdtrAcct>
               <Id>
                  <IBAN>DE05500105173195282731</IBAN>
               </Id>
            </CdtrAcct>
         </CdtTrfTxInf>
         <CdtTrfTxInf>
            <PmtId>
               <EndToEndId>NOTPROVIDED</EndToEndId>
            </PmtId>
            <Amt>
               <InstdAmt Ccy="EUR">1000.0</InstdAmt>
            </Amt>
            <CdtrAgt>
               <FinInstnId>
                  <BIC>UINVDEFFXXX</BIC>
               </FinInstnId>
            </CdtrAgt>
            <Cdtr>
               <Nm>Carl White</Nm>
            </Cdtr>
            <CdtrAcct>
               <Id>
                  <IBAN>DK5250511963137134</IBAN>
               </Id>
            </CdtrAcct>
         </CdtTrfTxInf>
         <CdtTrfTxInf>
            <PmtId>
               <EndToEndId>NOTPROVIDED</EndToEndId>
            </PmtId>
            <Amt>
               <InstdAmt Ccy="EUR">5000.0</InstdAmt>
            </Amt>
            <CdtrAgt>
               <FinInstnId>
                  <BIC>SWBSDESSXXX</BIC>
               </FinInstnId>
            </CdtrAgt>
            <Cdtr>
               <Nm>Frank Black</Nm>
            </Cdtr>
            <CdtrAcct>
               <Id>
                  <IBAN>CZ7627005991764514418145</IBAN>
               </Id>
            </CdtrAcct>
         </CdtTrfTxInf>
      </PmtInf>
   </CstmrDrctDbtInitn>
</Document>

```

## License

The MIT License (MIT)

Copyright (c) 2016 JohannesLaier

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

