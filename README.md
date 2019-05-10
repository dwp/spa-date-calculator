# SpringBoot

This project is intended to be used by SpringBoot Applications. You will need to add the below annotation to your Application or ApplicationConfig class:

`@ComponentScan({"uk.gov.dwp.gysp.spadatecalculator"})`

If @ComponentScan is already in use it will need to be expanded, for example:

`@ComponentScan({"uk.gov.dwp.gysp.foo", "uk.gov.dwp.gysp.spadatecalculator"})`

# SpDateCalculator

This class calculates entitlement to state pension using gender and date of birth. 

Depending on the gender(Gender.MALE or Gender.FEMALE) and dob provided a map will be returned containing a state pension entitlement date and whether there is a current entitlement(Mature Claim or Pre-mature Claim).

For example: 

`final Date dob = new GregorianCalendar(1955, Calendar.AUGUST, 01).getTime();`

`SpDateCalculator.findSpDate(dob, Gender.MALE)`

will return {spaDate=1627794000000, message=Pre-Mature claim}. Parsed spaDate is Sun Aug 01 06:00:00 BST 2021.

`final Date dob = new GregorianCalendar(1951, Calendar.AUGUST, 01).getTime();`

`SpDateCalculator.findSpDate(dob, Gender.MALE)`

will return {spaDate=1470027600000, message=Mature claim}. Parsed spaDate is Mon Aug 01 06:00:00 BST 2016.

#### Project inclusion

Dependency reference:

    <dependency>
        <groupId>uk.gov.dwp.gysp</groupId>
        <artifactId>spa-date-calculator</artifactId>
        <version>1.0.0</version>
    </dependency>
    
#### Example of use

    import uk.gov.dwp.gysp.spadatecalculator.Gender;
    import uk.gov.dwp.gysp.spadatecalculator.SpDateCalculator;

_Declaration_

`@Autowired`

`private SpDateCalculator spDateCalculator;`

_Use example_

`final Date dob = new GregorianCalendar(1955, Calendar.AUGUST, 01).getTime();`

`spDateCalculator.findSpDate(dob, Gender.MALE)`

# Maintenance and Contributing

This project is maintained by [GYSPDevTeam](mailto:longbenton.gysp_development@dwp.gov.uk?Subject=Pull%20Request)

See [CONTRIBUTING.md](./CONTRIBUTING.md) for contributing.